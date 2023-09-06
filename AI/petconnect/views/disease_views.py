from flask import Blueprint, request, jsonify
import os
import cv2
from skimage.feature import hog
import pickle
import pymysql

bp = Blueprint('disease', __name__, url_prefix='/disease')

# 현재 파일의 디렉토리 경로
current_dir = os.path.dirname(os.path.abspath(__file__))

# disease_model.pkl의 상대 경로 계산
disease_model_path = os.path.join(current_dir, '..', '..', 'disease', 'SVM-Classifier', 'disease.pkl')

# SVM 모델 로드
with open(disease_model_path, 'rb') as model_file:
    svm_model = pickle.load(model_file)

categories = ['atopy', 'hotspots', 'hair_loss', 'normal']

# db
def db_connector():
    db_params = {
        'host': 'localhost',
        'user': 'root',
        'password': 'Qwer12345678!',
        'db': 'pet_connect',
        'charset': 'utf8',
        'cursorclass': pymysql.cursors.DictCursor
    }
    
    connector = pymysql.connect(**db_params)
    return connector

@bp.route('/', methods=['POST'])
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

        # Store the result in the database
        try:
            db_conn = db_connector()
            cursor = db_conn.cursor()

            query = "INSERT INTO disease_results (dogRegistNum, predicted_category) VALUES (%s, %s)"
            values = (request.form['dogRegistNum'], predicted_category)
            cursor.execute(query, values)
            db_conn.commit()

            cursor.close()
            db_conn.close()

            return jsonify({'result': predicted_category})

        except Exception as e:
            print("Error storing result in the database:", e)
            return jsonify({'error': 'Failed to store result in the database'})

@bp.route('/<dogRegistNum>', methods=['GET'])
def get_disease_result(dogRegistNum):
    try:
        db_conn = db_connector()
        cursor = db_conn.cursor()

        query = "SELECT predicted_category FROM disease_results WHERE dogRegistNum = %s"
        values = (dogRegistNum,)
        cursor.execute(query, values)
        result = cursor.fetchone()

        cursor.close()
        db_conn.close()

        if result:
            return jsonify({'result': result['predicted_category']})
        else:
            return jsonify({'error': 'No result found'})

    except Exception as e:
        print("Error retrieving result from database:", e)
        return jsonify({'error': 'Failed to retrieve result from database'})
