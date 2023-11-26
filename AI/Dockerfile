FROM python:3.7

COPY . /app
WORKDIR /app

RUN echo server will be running on 5000

RUN apt-get update
RUN apt-get install libgl1-mesa-glx -y

RUN pip install -r requirements.txt
RUN pip install opencv-python
RUN pip install opencv-contrib-python
RUN pip install scikit-image

CMD ["python" , "app.py"]
