import os
import argparse
import cv2
import numpy as np

from PIL import Image
from histo_clahe import histo_clahe

parser = argparse.ArgumentParser(description='Argparse Tutorial')
parser.add_argument('--dir', default='7',help='dataset directory')
parser.add_argument('--savedir', default='./Dog-Data/train',help='save directory')
opt = parser.parse_args()

os_path = 'C:/Users/roger/OneDrive/바탕 화면/pet-connect/AI/nose/SVM-Classifier'
os.chdir(os_path)

path =  './image/' + opt.dir
print("현재 경로는 = " + os.getcwd())
file_list = os.listdir(path)

rotate = [0, 15, 30, -15, -30]

def createFolder(directory):
    try:
        if not os.path.exists(directory):
            os.makedirs(directory)
    except OSError:
        print ('Error: Creating directory. ' +  directory)


for file in file_list:
    s = os.path.splitext(file)
    savedir = []
    createFolder(opt.savedir + '/' + opt.dir)
    #파일 저장 디렉토리 ./Dog-Data/train/imagename-i.jpg
    for i in range(10):
        savedir.append(opt.savedir + '/' + opt.dir + '/' + s[0] + '-' + str(i) + s[1])

    #사이즈 1 rotate 저장
    img = histo_clahe(path + '/' + file)
    height, width, channel = img.shape

    for i in range(5):
        matrix = cv2.getRotationMatrix2D((width/2, height/2), rotate[i], 1)
        dst = cv2.warpAffine(img, matrix, (width, height))
        cv2.imwrite(savedir[i],dst)

    #사이즈 1/2 rotate 저장
    img = cv2.resize(img,(int(width / 2), int(height / 2)))
    height, width, channel = img.shape

    for i in range(5):
        matrix = cv2.getRotationMatrix2D((width/2, height/2), rotate[i], 1)
        dst = cv2.warpAffine(img, matrix, (width, height))
        cv2.imwrite(savedir[i+5],dst)
    