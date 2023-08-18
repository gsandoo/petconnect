import cv2
import numpy as np

def histo_clahe(img_dir):
    img_array = np.fromfile(img_dir, np.uint8)
    img = cv2.imdecode(img_array, cv2.IMREAD_COLOR)
    height, width, channel = img.shape

    #사이즈가 너무 크면 반으로 줄인다. 인스타 이미지 사이즈 640x640
    while height >= 600 or width >=600:
        img = cv2.resize(img,(int(width / 2), int(height / 2)))
        height, width, channel = img.shape

    # YUV 컬러스페이스로 변경
    img_yuv = cv2.cvtColor(img, cv2.COLOR_BGR2YUV)

    #밝기 채널에 대해서 이퀄라이즈 적용
    img_eq = img_yuv.copy()
    img_eq[:,:,0] = cv2.equalizeHist(img_eq[:,:,0])
    img_eq = cv2.cvtColor(img_eq, cv2.COLOR_YUV2BGR)

    #밝기 채널에 대해서 CLAHE 적용
    img_clahe = img_yuv.copy()
    clahe = cv2.createCLAHE(clipLimit=3.0, tileGridSize=(8,8)) #CLAHE 생성
    img_clahe[:,:,0] = clahe.apply(img_clahe[:,:,0])           #CLAHE 적용
    img_clahe = cv2.cvtColor(img_clahe, cv2.COLOR_YUV2BGR)

    # cv2.imshow("answer",img_clahe)
    # cv2.waitKey(50000)
    print("img clahe 리턴합니다~~")
    return img_clahe[:,:,:]