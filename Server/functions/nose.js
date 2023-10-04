/* eslint-disable indent */
/**
/* eslint-disable max-len */

// Dependencies for callable functions.
const {onCall, HttpsError} = require("firebase-functions/v2/https");

// Dependencies for the addMessage function.
const {getFirestore} = require("firebase-functions/v2/firestore");
const {getStorage} = require("firebase-admin/storage");

const firebase = require("firebase-admin");
const axios = require("axios");
const {Timestamp} = require("firebase-admin/firestore");

// firebase.initializeApp();

const bucket = getStorage().bucket("pet-connect-cdc02.appspot.com");

// 비문 등록
exports.noseRegister = onCall(async (request) => {
  const data = request.data;
  if (!data) {
    console.log("no data");
    throw new HttpsError("failed-precondition", "no submit data");
  }
  const formData = new FormData();
  // request 정보를 변수에 저장
  const fileName = data.fileName;

  // filename 을 통해서 파이어베이스 버킷에서 이미지 다운로드 및 formdata에 추가
  for(let i=1; i < 6;i++){
    const file = await bucket.file(`image/${fileName}/nose${i}`).download();
    const imageBuffer = file[0];
    const arrayBuffer = new Uint8Array(imageBuffer).buffer;
    const blob = new Blob([arrayBuffer]);
    formData.append(`dogNose${(i).toString()}`, blob);
  }
  const file = await bucket.file(`image/${fileName}/profile`).download();
  const imageBuffer = file[0];
  const arrayBuffer = new Uint8Array(imageBuffer).buffer;
  const blob = new Blob([arrayBuffer]);
  formData.append("dogProfile", blob);

  const result = await axios.post(
    "http://192.168.3.213:8000/register",
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    },
  ).catch(function (err) {
    if (err.response) {
      // 요청이 이루어졌으며 서버가 2xx의 범위를 벗어나는 상태 코드로 응답했습니다.
      console.log(err.response.data);
      console.log(err.response.status);
      console.log(err.response.headers);
    }
    else if (err.request) {
      // 요청이 이루어 졌으나 응답을 받지 못했습니다.
      // `error.request`는 브라우저의 XMLHttpRequest 인스턴스 또는
      // Node.js의 http.ClientRequest 인스턴스입니다.
      console.log(err.request);
    }
    else {
      // 오류를 발생시킨 요청을 설정하는 중에 문제가 발생했습니다.
      console.log('Error', err.message);
    }
    console.log(err.config);
  });
  console.log(result.data);
  return result.data
});

// 비문 조회
exports.noseCheck = onCall(async (request) => {
    const data = request.data;
    if (!data) {
      console.log("no data");
      throw new HttpsError("failed-precondition", "no submit data");
    }
    const formData = new FormData();
    const fileName = data.fileName;
    const file = await bucket.file(`image/${fileName}/check`).download();
    const imageBuffer = file[0];
    const arrayBuffer = new Uint8Array(imageBuffer).buffer;
    const blob = new Blob([arrayBuffer]);
  
    formData.append("dogNose", blob);
  
    const result = await axios.post(
      "http://192.168.3.213:8000/lookup",
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      },
    ).catch(function (err) {
      if (err.response) {
        // 요청이 이루어졌으며 서버가 2xx의 범위를 벗어나는 상태 코드로 응답했습니다.
        console.log(err.response.data);
        console.log(err.response.status);
        console.log(err.response.headers);
      }
      else if (err.request) {
        // 요청이 이루어 졌으나 응답을 받지 못했습니다.
        // `error.request`는 브라우저의 XMLHttpRequest 인스턴스 또는
        // Node.js의 http.ClientRequest 인스턴스입니다.
        console.log(err.request);
      }
      else {
        // 오류를 발생시킨 요청을 설정하는 중에 문제가 발생했습니다.
        console.log('Error', err.message);
      }
      console.log(err.config);
    });
    console.log(result.data);
    return result.data
  });
  