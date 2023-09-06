import os
import cv2
import numpy as np
from skimage.feature import hog
from sklearn.svm import SVC
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import pickle

current_dir = os.path.dirname(os.path.abspath(__file__))

# Check if the disease.pkl file already exists
model_file_path = os.path.join(current_dir, 'disease.pkl')
if not os.path.exists(model_file_path):
    # Load training data and labels
    training_data = []
    training_labels = []
    categories = ['atopy', 'hotspots', 'hair_loss', 'normal']

    for label, category in enumerate(categories):
        category_folder = os.path.join(current_dir, 'photos', 'training', category)
        for filename in os.listdir(category_folder):
            image_path = os.path.join(category_folder, filename)
            image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
            preprocessed_image = cv2.resize(image, (64, 64))
            hog_features = hog(preprocessed_image, orientations=8, pixels_per_cell=(16, 16), cells_per_block=(1, 1))
            training_data.append(hog_features)
            training_labels.append(label)

    # Convert lists to numpy arrays
    X_train = np.array(training_data)
    y_train = np.array(training_labels)

    # Create and train the SVM model with hyperparameter tuning
    svm_model = SVC(kernel='linear', C=10.0)  # Adjust C value for tuning
    X_train, X_val, y_train, y_val = train_test_split(X_train, y_train, test_size=0.2, random_state=42)  # Split data for validation
    svm_model.fit(X_train, y_train)

    # Predict using validation data
    val_predictions = svm_model.predict(X_val)

    # Calculate accuracy on validation data
    val_accuracy = accuracy_score(y_val, val_predictions) * 100
    print(f'Validation Accuracy: {val_accuracy:.2f}%')

    # Save the trained model using pickle
    with open(model_file_path, 'wb') as model_file:
        pickle.dump(svm_model, model_file)
    print("disease model trained and saved.")
else:
    print("disease_model.pkl already exists. Skipping training.")

# Path to the validation data folders
validation_folder = os.path.join(current_dir, 'photos', 'validation')

# Initialize counters
total_images = 0
correct_predictions = 0

# Loop through each folder
categories = ['atopy', 'hotspots', 'hair_loss', 'normal']
for label, category in enumerate(categories):
    folder_path = os.path.join(validation_folder, category)
    image_files = os.listdir(folder_path)

    # Loop through each image in the folder
    for image_file in image_files:
        image_path = os.path.join(folder_path, image_file)
        image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

        # Preprocess the image (resize, HOG)
        preprocessed_image = cv2.resize(image, (64, 64))
        hog_features = hog(preprocessed_image, orientations=8, pixels_per_cell=(16, 16), cells_per_block=(1, 1))

        # Predict the category
        predicted_label = svm_model.predict([hog_features])[0]

        # Compare with actual label
        if predicted_label == label:
            correct_predictions += 1

        total_images += 1
