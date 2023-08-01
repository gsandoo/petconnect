from .controller.example_controllers import example_bp

def routes_list(app):
    app.register_blueprint(example_bp)
    return app