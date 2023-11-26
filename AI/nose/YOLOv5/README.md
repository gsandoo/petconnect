
## Requirements

Python 3.8 or later with all [requirements.txt](https://github.com/ultralytics/yolov5/blob/master/requirements.txt) dependencies installed, including `torch>=1.7`. To install run:
```bash
$ pip install -r requirements.txt
```
## detect
```bash
$ python ../YOLOv5/detect.py --source ../SVM-Classifier/rawimage/6  --weights ../YOLOv5/best.pt --option register --conf 0.25
$ python ../YOLOv5/detect.py --source ../SVM-Classifier/testimage/6.jpg  --weights ../YOLOv5/best.pt --option test --conf 0.25
```

## Inference

detect.py runs inference on a variety of sources, downloading models automatically from the [latest YOLOv5 release](https://github.com/ultralytics/yolov5/releases) and saving results to `runs/detect`.
```bash
$ python detect.py --source 0  # webcam
                            file.jpg  # image 
                            file.mp4  # video
                            path/  # directory
                            path/*.jpg  # glob
                            rtsp://170.93.143.139/rtplive/470011e600ef003a004ee33696235daa  # rtsp stream
                            rtmp://192.168.1.105/live/test  # rtmp stream
                            http://112.50.243.8/PLTV/88888888/224/3221225900/1.m3u8  # http stream
```

## save directory

YOLOv5/runs/detect/
