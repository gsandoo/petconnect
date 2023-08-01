from dotenv import load_dotenv
import os

# .env 파일 auto load
load_dotenv()

class Config(object):
    TESTING = False
    SECRET_KEY = os.getenv('SECRET_KEY')
    SQLALCHEMY_TRACK_MODIFICATIONS = False

class DevelopmentConfig(Config):
    DEBUG = True
    SQLALCHEMY_DATABASE_URI = f"mysql://{os.getenv('DB_USER')}:" \
                              f"{os.getenv('DB_PWD')}@" \
                              f"{os.getenv('DB_HOST')}:" \
                              f"{os.getenv('DB_PORT')}/" \
                              f"{os.getenv('DB_NAME')}?charset=utf8"

class ProductionConfig(Config):
    SQLALCHEMY_DATABASE_URI = f"mysql://{os.getenv('DB_USER')}:" \
                              f"{os.getenv('DB_PWD')}@" \
                              f"{os.getenv('DB_HOST')}:" \
                              f"{os.getenv('DB_PORT')}/" \
                              f"{os.getenv('DB_NAME')}?charset=utf8"

class TestingConfig(Config):
    TESTING = True