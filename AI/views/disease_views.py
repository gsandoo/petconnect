from flask import Blueprint, request, jsonify
import os
import cv2
from skimage.feature import hog
import pickle
import pymysql

#path adjusting
def get_path(path):
    change_path = path.replace("\\",'/')
    return change_path


bp = Blueprint('disease', __name__, url_prefix='/')

# disease_model.pkl의 상대 경로 계산
path = '../disease/SVM-Classifier/disease_model.pkl'
classify_path = '/app/disease/SVM-Classifier/disease_model.pkl'
# classify_path = get_path(os.path.abspath(path))

with open(classify_path, 'rb') as model_file:
    svm_model = pickle.load(model_file)

categories = ['atopy', 'hotspots', 'hair_loss', 'normal']


@bp.route('/disease', methods=['POST'])
def analyze_disease():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'})

    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'})

    if file:
        # Create the 'uploads' directory if it doesn't exist
        if not os.path.exists('uploads'):
            os.makedirs('uploads')

        # Save the uploaded image to a temporary location
        image_path = os.path.join('uploads', file.filename)
        file.save(image_path)

        # Load and preprocess the image
        image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
        preprocessed_image = cv2.resize(image, (64, 64))
        hog_features = hog(preprocessed_image, orientations=8, pixels_per_cell=(16, 16), cells_per_block=(1, 1))

        # Predict the disease category using SVM model
        predicted_label = svm_model.predict([hog_features])[0]
        predicted_category = categories[predicted_label]

        # Remove the temporary image file
        os.remove(image_path)

        # Return the result as a JSON response
        return jsonify({'result': predicted_category, 'message': 'success'})
        

# @bp.route('/<dogRegistNum>', methods=['GET'])
# def get_disease_result(dogRegistNum):
#     try:
#         db_conn = db_connector()
#         cursor = db_conn.cursor()

#         query = "SELECT predicted_category FROM disease_results WHERE dogRegistNum = %s"
#         values = (dogRegistNum,)
#         cursor.execute(query, values)
#         result = cursor.fetchone()

#         cursor.close()
#         db_conn.close()

#         if result:
#             return jsonify({'result': result['predicted_category']})
#         else:
#             return jsonify({'error': 'No result found'})

#     except Exception as e:
#         print("Error retrieving result from database:", e)
#         return jsonify({'error': 'Failed to retrieve result from database'})

def db_connector():
    connector = pymysql.connect(host='petconnect.cfqtdjr2iyqh.ap-northeast-2.rds.amazonaws.com',
                                  user='admin',
                                  password='Qwer12345678!',
                                  db='petconnect',
                                  charset='utf8')
    return connector