import os
import cv2
import numpy as np
from tensorflow.python.keras.models import Sequential
from tensorflow.python.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Dropout
from tensorflow.python.keras.optimizers import Adam

current_dir = os.path.dirname(os.path.abspath(__file__))

# Load labels
categories = ['atopy', 'hotspots', 'hair_loss', 'normal']

# Load training data and labels
training_data = []
training_labels = []

# Loop through each folder
for label, category in enumerate(categories):
    folder_path = os.path.join(current_dir, 'photos', 'training', category)
    image_files = os.listdir(folder_path)

    # Loop through each image in the folder
    for image_file in image_files:
        image_path = os.path.join(folder_path, image_file)
        image = cv2.imread(image_path, cv2.IMREAD_COLOR)
        preprocessed_image = cv2.resize(image, (64, 64))
        training_data.append(preprocessed_image)
        training_labels.append(label)

# Convert lists to numpy arrays
training_data = np.array(training_data)
training_labels = np.array(training_labels)

# Normalize pixel values
training_data = training_data.astype('float32') / 255.0

# Create and compile the CNN model
model = Sequential([
    Conv2D(32, (3, 3), activation='relu', input_shape=(64, 64, 3)),
    MaxPooling2D(2, 2),
    Conv2D(64, (3, 3), activation='relu'),
    MaxPooling2D(2, 2),
    Conv2D(128, (3, 3), activation='relu'),
    MaxPooling2D(2, 2),
    Flatten(),
    Dense(512, activation='relu'),
    Dropout(0.5),
    Dense(4, activation='softmax')
])
model.compile(optimizer=Adam(lr=0.0001), loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train the model
model.fit(training_data, training_labels, epochs=10, batch_size=32)

# Save the model weights
model.save_weights('disease_weights.h5')
