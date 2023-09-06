import cv2
import os

def apply_clahe(input_image_path, output_image_path):
    # Load the input image
    image = cv2.imread(input_image_path)

    # Convert the image to grayscale
    gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Apply CLAHE preprocessing
    clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8, 8))
    clahe_image = clahe.apply(gray_image)

    # Save the CLAHE preprocessed image
    cv2.imwrite(output_image_path, clahe_image)

def preprocess_images_in_folder(input_folder, output_folder):
    # Loop through each subfolder (atopy, hair_loss, hot_spots, normal) in the input folder
    for subfolder in os.listdir(input_folder):
        subfolder_path = os.path.join(input_folder, subfolder)

        # Create a corresponding subfolder in the output folder if it doesn't exist
        output_subfolder_path = os.path.join(output_folder, subfolder)
        os.makedirs(output_subfolder_path, exist_ok=True)

        # Loop through each image in the subfolder
        for filename in os.listdir(subfolder_path):
            input_image_path = os.path.join(subfolder_path, filename)
            output_image_path = os.path.join(output_subfolder_path, filename)

            # Apply CLAHE preprocessing to the image
            apply_clahe(input_image_path, output_image_path)

if __name__ == "__main__":
    # Input and output folder paths
    training_input_folder = "photos/training"
    training_output_folder = "photos/training_clahe"

    validation_input_folder = "photos/validation"
    validation_output_folder = "photos/validation_clahe"

    # Apply CLAHE preprocessing to images in training and validation folders
    preprocess_images_in_folder(training_input_folder, training_output_folder)
    preprocess_images_in_folder(validation_input_folder, validation_output_folder)
