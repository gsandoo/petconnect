/* eslint-disable indent */
/**
/* eslint-disable max-len */

// Dependencies for callable functions.
const {onCall, HttpsError} = require("firebase-functions/v2/https");

// Dependencies for the addMessage function.
const {getStorage} = require("firebase-admin/storage");

const firebase = require("firebase-admin");
const axios = require("axios");
const { getFirestore, Timestamp, FieldValue} = require('firebase-admin/firestore');


firebase.initializeApp();

const bucket = getStorage().bucket("pet-connect-cdc02.appspot.com");


exports.health = onCall(async (request) => {
  const data = request.data;
  if (!data) {
    console.log("no data");
    throw new HttpsError("failed-precondition", "no submit data");
  }
  const formData = new FormData();
  const dogRegistNum = data.dogId;
  const fileName = data.fileName;
  const kind = data.kind;
  const side = data.side;
  const file = await bucket.file(`image/${kind}/${fileName}`).download();
  const imageBuffer = file[0];
  const arrayBuffer = new Uint8Array(imageBuffer).buffer;
  const blob = new Blob([arrayBuffer]);

  formData.append("file", blob);
  formData.append("dogRegistNum", dogRegistNum); 

  const result = await axios.post(
    `http://192.168.3.213:8000/${kind}/`,
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    },
  );
  if (result.data.message == "fail"){
    return result.data
  }
  const db = getFirestore();
  let dbData = {};
  if(kind == "eye") {
    dbData = {
      "dog_id": dogRegistNum,
      "health": result.data.result,
      "create_time": Timestamp.fromDate(new Date()),
      "side": side,
    };
  }else{
    dbData = {
      "dog_id": dogRegistNum,
      "health": result.data.result,
      "create_time": FieldValue.serverTimestamp(),
    };
  }
  console.log(dbData);
  
  const dogRef = await db.collection(`${kind.toString()}`).doc()
  dogRef.set(dbData)
  const docId = dogRef.id


  const responseData = {
    "message": "success", "docId": docId, "result": result.data.result,
  };
  console.log(responseData);

  return responseData;
});
