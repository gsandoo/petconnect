#ORM적용하기 위한 설정파일
import os
BASE_DIR=os.path.dirname(__file__)
SQLALCHEMY_DATABASE_URI = 'mysql://root:Qwer12345678!/pet_connect'
SQLALCHEMY_TRACK_MODIFICATIONS = False