import os
import cv2
import numpy as np
from skimage.feature import hog
import pickle
from sklearn.svm import SVC
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

current_dir = os.path.dirname(os.path.abspath(__file__))

# Load your trained SVM model here
model_file_path = os.path.join(current_dir, 'eye.pkl')
with open(model_file_path, 'rb') as model_file:
    svm_model = pickle.load(model_file)

# Path to the validation data folders
validation_folder = os.path.join(current_dir, 'photos', 'validation')

# Initialize counters
total_images = 0
correct_predictions = 0

def apply_augmentations(image):
    # Apply image augmentations (rotation and grayscale conversion)
    angle = np.random.randint(-15, 15)  # Random rotation angle between -15 and 15 degrees
    rotated_image = rotate_image(image, angle)
    grayscale_image = cv2.cvtColor(rotated_image, cv2.COLOR_BGR2GRAY)
    return grayscale_image

def rotate_image(image, angle):
    rows, cols, _ = image.shape
    rotation_matrix = cv2.getRotationMatrix2D((cols/2, rows/2), angle, 1)
    rotated_image = cv2.warpAffine(image, rotation_matrix, (cols, rows))
    return rotated_image

def histo_clahe(img_path):
    # Load and preprocess the image
    img = cv2.imread(img_path)
    # Apply histogram equalization and CLAHE processing
    processed_img = apply_augmentations(img)
    return processed_img

def extract_features(image):
    # Extract HOG features from the image
    hog_features = hog(image, orientations=8, pixels_per_cell=(16, 16), cells_per_block=(1, 1))
    return hog_features

# Loop through each folder
for label, folder_name in enumerate(['cataracts', 'cherry_eye', 'normal']):
    folder_path = os.path.join(validation_folder, folder_name)
    image_files = os.listdir(folder_path)

    # Loop through each image in the folder
    for image_file in image_files:
        image_path = os.path.join(folder_path, image_file)
        image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
        
        # Apply histogram equalization and CLAHE processing
        processed_image = histo_clahe(image_path)
        
        # Preprocess the image (resize, normalize, etc.)
        preprocessed_image = cv2.resize(processed_image, (64, 64))
        
        # Extract features using HOG
        hog_features = extract_features(preprocessed_image)
        
        # Predict the category
        reshaped_features = hog_features.reshape(1, -1)
        predicted_label = svm_model.predict(reshaped_features)
        
        # Compare with actual label
        if predicted_label == label:
            correct_predictions += 1

        total_images += 1

# Calculate accuracy
accuracy = (correct_predictions / total_images) * 100
print(f'Accuracy: {accuracy:.2f}%')
