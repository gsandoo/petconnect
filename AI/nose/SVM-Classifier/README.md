# SVM-Classifier 
---
SIFT 특징추출, BOW(Bag Of Word), 모델: SVM 사용

- 입력 이미지로부터 SIFT 특징을 추출한 후 KMeans 모델을 사용하여 BOW를 세운다. 각각의 이미지는 100차원의 벡터로 벡터화된다.
- train 시 SVM, KNN 모델을 사용하고 정확도를 비교한다.
- 강아지 비문을 분류하기위한 모델이다.


### Requirements
---
1. AI 폴더의 리드미 확인하고 venv 가상환경과 SVM-Classifier폴더 내 가상환경을 만들어준다.

2. 가상환경 위에 각각의 requirements.txt를 다운 받아준다. 
    - ``` pip install -r requirements.txt ```
    - **opencv파일은AI폴더에있습니다.**

### preprocess.py 사용법
---
1. image 폴더에 image/0 식으로 폴더 생성
    - 안에 비문 이미지 있어야 함 

2. AI 폴더의 venv 가상환경 실행 

3. preprocess.py 내 테스트 경로 주석 해제, 미등록강아지 경로 주석처리 

4. 테스트할 데이터를 image 디렉토리에 넣고 디렉토리이름을 매개변수로 넣어서  preprocess.py 실행

### Classifier.py 사용법
---
1. SVM-Classifier 폴더 내 venv 가상환경 실행

2. Classifier.py 실행
