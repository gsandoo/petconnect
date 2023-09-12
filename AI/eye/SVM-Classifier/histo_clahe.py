import cv2
import numpy as np

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
