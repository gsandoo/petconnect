from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate

db = SQLAlchemy()
migrate = Migrate()

def create_app():
    app = Flask(__name__)

    # config
    config = app.config.get('ENV')
    if config == 'production':
        app.config.from_object('config.ProductionConfig')
    elif config == 'testing':
        app.config.from_object('config.TestingConfig')
    else:
        app.config.from_object('config.DevelopmentConfig')

    # database
    from nose.model import example_models
    db.init_app(app)
    migrate.init_app(app, db)

    # routes list
    from .routes import routes_list
    routes_list(app)

    @app.route('/')
    def hello_world():
        return 'hello world!'

    return app