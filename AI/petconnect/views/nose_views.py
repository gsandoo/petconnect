import shutil

from flask import Flask, request, jsonify, render_template,Blueprint
import datetime
import os
import random
import subprocess
import pymysql



bp= Blueprint('main',__name__,url_prefix='/')
@bp.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        dogNose2 = request.files['dogNose2']
        dogNose3 = request.files['dogNose3']
        dogNose4 = request.files['dogNose4']
        dogNose5 = request.files['dogNose5']
        global details
        details = request.form
        profile = request.files['dogProfile']
        forlookup = request.files['dogNose1']

        now = datetime.datetime.now()
        formoment = str(now.year) + str(now.month) + str(now.hour) + str(now.minute) + str(now.second)
        formoment1 = str(formoment)
        print("formoment1 = " + formoment1)


    # try:
    #     # for yolov5
    #     createFolder('./SVM-Classifier/testimage/%s' % (formoment1))
    #     forlookup.save('./SVM-Classifier/testimage/%s/%s.jpg' % (formoment1, formoment1))
    #     os.system(
    #         'cd YOLOv5 && python ../YOLOv5/detect.py --source ../SVM-Classifier/testimage/%s --weights ../YOLOv5/best.pt --option test --conf 0.25' % (
    #             formoment1))
    # except Exception as e1:
    #     print("[등록시 조회] yolov5 코드가 안돌아가서 실패", e1)
    #     return jsonify({'message': 'fail'})


    try:
        print("try 문 시작")
        # 5장 중 1장만 ml코드 돌리기
        result = getSVMResultForRegister(formoment1)
        print("37 ...여기까지만 와라2  " + result)
        compare = result.split(',')
        print("여기까지만 와라3")
        print(compare)
        if compare == ['']:
            raise Exception('error')
    except Exception as e:
        print("ML코드가 안돌아가서 등록 실패", e)
        return jsonify({'message': 'fail'})

    if compare[1] == '등록된강아지':
        try:
            isRegistered = db_connector()
            cursor = isRegistered.cursor()
            foundDog = compare[0]
            print(foundDog)
            lookup_sql = "SELECT * FROM pet WHERE uniqNum='%s'" % (foundDog)
            cursor.execute(lookup_sql)
            registeredPetDatas = cursor.fetchall()
            print(registeredPetDatas)
            registeredPetName = registeredPetDatas[0][1]
            registeredPetBreed = registeredPetDatas[0][2]
            registeredPetSex = registeredPetDatas[0][4]
            registeredPetBirthYear = registeredPetDatas[0][3]
            registeredPetProfile = registeredPetDatas[0][5] + '.jpg'

            return jsonify(
                {'data': {'dogRegistNum': foundDog, 'dogName': registeredPetName, 'dogBreed': registeredPetBreed,
                          'dogSex': registeredPetSex,
                          'dogBirthYear': registeredPetBirthYear, 'dogProfile': registeredPetProfile,
                          'isSuccess': False}, 'message': '이미 등록된 강아지입니다.'})

        except Exception as e:
            print('isRegistered db에서 예외가 발생했습니다', e)
            return jsonify({'message': 'fail'})

        finally:
            if isRegistered:
                cursor.close()
                isRegistered.close()

    # 미등록강아지인 경우
    else:
        reg_num = uniquenumber(details['phoneNum'][7:11])
        print('reg_num =' + reg_num)
        # 프로필 이미지
        profile.save('./static/img/%s' % (reg_num) + '.jpg')
        profileUrl = "profileImg/%s" % (reg_num)

        createFolder('./SVM-Classifier/image/%s' % (reg_num))
        createFolder('./SVM-Classifier/rawimage/%s' % (reg_num))
        # 이미지 5장, key = filename[] 저장 for preprocess
        source = './SVM-Classifier/testimage/%s/%s.jpg' % (formoment1, formoment1)
        destination = './SVM-Classifier/rawimage/%s/0.jpg' % (reg_num)
        shutil.copyfile(source, destination)
        dogNose2.save('./SVM-Classifier/rawimage/%s/' % (reg_num) + '1.jpg')
        dogNose3.save('./SVM-Classifier/rawimage/%s/' % (reg_num) + '2.jpg')
        dogNose4.save('./SVM-Classifier/rawimage/%s/' % (reg_num) + '3.jpg')
        dogNose5.save('./SVM-Classifier/rawimage/%s/' % (reg_num) + '4.jpg')

        # try:
        #     os.system(
        #         'cd YOLOv5 && python ../YOLOv5/detect.py --source ../SVM-Classifier/rawimage/%s --weights ../YOLOv5/best.pt --option register --conf 0.25' % (
        #             reg_num))

        # except Exception as e:
        #     print("[등록] 미등록 강아지 yolo 코드 예외 발생")
        #     return jsonify({'messgae:fail'})

        try:
            os.system('cd SVM-Classifier && python preprocess.py --dir %s' % (reg_num))
        except Exception as e:
            print("[등록]미등록강아지 preprocess ML코드 예외 발생")
            return jsonify({'message': 'fail'})

        # #5장 중에 첫번째 장 사진 복사 -> 조회
        # source ='./SVM-Classifier/image/%s/0.jpg' %(reg_num)
        # destination = './SVM-Classifier/Dog-Data/test/%s.jpg' %(reg_num)
        # shutil.copyfile(source, destination)

        # 연락처 중복확인  (기존 등록된 유저 일때)
        try:
            alreadyRegistered = db_connector()
            cursor = alreadyRegistered.cursor()

            phone = request.form['phoneNum']
            phone_confirm = "SELECT 1 FROM registrant WHERE regphone = '%s' " % (phone)
            cursor.execute(phone_confirm)
            data = cursor.fetchall()

        except Exception as e:
            print("alreadyRegistered DB에서 예외가 발생했습니다", e)
            return jsonify({'message': 'fail'})

        # 기존 등록 유저 & 새로운 강아지 등록
        if data:

            try:
                pk = "select id from registrant WHERE regphone = '%s'" % (phone)
                cursor.execute(pk)
                pk1 = cursor.fetchone()

                pet_sql = "INSERT INTO pet (petname,petbreed,petbirth,petgender,petprofile,reg_id,uniquenumber) VALUES(%s,%s,%s,%s,%s,%s,%s)"
                val1 = (
                details['dogName'], details['dogBreed'], details['dogBirthYear'], details['dogSex'], profileUrl, pk1,
                reg_num)

                cursor.execute(pet_sql, val1)
                # 가장 최근 insert id 불러오기
                reg_send = "SELECT last_insert_id();"
                cursor.execute(reg_send)
                latestid = cursor.fetchone()

                # db등록 정보 가져오기
                fetchDB = "SELECT * FROM pet WHERE id ='%s'" % (latestid[0])
                cursor.execute(fetchDB)
                send = cursor.fetchall()
                # print(send)
                petname = send[0][1]
                petbirth = send[0][3]
                petgender = send[0][4]
                petprofile = send[0][5] + '.jpg'
                petnumber = send[0][7]
                petbreed = send[0][2]
                alreadyRegistered.commit()

                return jsonify({'data': {'dogName': petname, 'dogRegistNum': petnumber, 'dogBreed': petbreed,
                                         'dogBirthYear': petbirth, 'dogSex': petgender, 'dogProfile': petprofile,
                                         'isSuccess': True}, 'message': '등록이 성공했습니다'})
            except Exception as e:
                alreadyRegistered.rollback()
                print("alreadyRegistered2에서 예외가 발생했습니다", e)
                return jsonify({'message': 'fail'})

            finally:
                if alreadyRegistered:
                    cursor.close()
                    alreadyRegistered.close()


        # 새로운 유저 & 새로운 강아지
        else:
            # registrant table에 insert
            try:
                newuser = db_connector()
                cursor = newuser.cursor()
                reg_sql = "INSERT INTO registrant (regname,regphone,regemail) VALUES(%s,%s,%s)"
                val = (details['registrant'], details['phoneNum'], details['email'])
                cursor.execute(reg_sql, val)

                # primarykey
                pk = "select id from registrant WHERE regphone='%s'" % (phone)
                cursor.execute(pk)
                rows = cursor.fetchone()

                # pet table에 insert
                pet_sql = "INSERT INTO pet (petname,petbreed,petbirth,petgender,petprofile,reg_id,uniquenumber) VALUES(%s,%s,%s,%s,%s,%s,%s)"
                val1 = (
                details['dogName'], details['dogBreed'], details['dogBirthYear'], details['dogSex'], profileUrl, rows,
                reg_num)

                cursor.execute(pet_sql, val1)

                # 가장 최근 insert id 불러오기
                new_reg_send = "SELECT last_insert_id();"
                cursor.execute(new_reg_send)
                new_latestid = cursor.fetchone()

                # db등록 정보 가져오기
                new_fetchDB = "SELECT * FROM pet WHERE id ='%s'" % (new_latestid[0])
                cursor.execute(new_fetchDB)
                new_all = cursor.fetchall()
                # print(new_all)
                new_petname = new_all[0][1]
                new_petbirth = new_all[0][3]
                new_petgender = new_all[0][4]
                new_petprofile = new_all[0][5] + '.jpg'
                new_petnumber = new_all[0][7]
                new_petbreed = new_all[0][2]
                newuser.commit()
                return jsonify(
                    {'data': {'dogName': new_petname, 'dogRegistNum': new_petnumber, 'dogBreed': new_petbreed,
                              'dogBirthYear': new_petbirth, 'dogSex': new_petgender, 'dogProfile': new_petprofile,
                              'isSuccess': True}, 'message': '등록이 성공했습니다'})

            except Exception as e:
                newuser.rollback()
                print("new user db에서 예외가 발생했습니다")
                return jsonify({'message': 'fail'})

            finally:
                if newuser:
                    cursor.close()
                    newuser.close()


# [조회 API]
@bp.route('/lookup', methods=['GET', 'POST'])
def lookup():
    if request.method == 'POST':
        lookupimg = request.files['dogNose']
        now1 = datetime.datetime.now()
        formomentLookup = str(now1.year) + str(now1.month) + str(now1.hour) + str(now1.minute) + str(now1.second)
        formomentLookup1 = str(formomentLookup)
        print(formomentLookup)

        # Yolov5
        try:
            createFolder('./SVM-Classifier/testimage/%s' % (formomentLookup1))
            lookupimg.save('./SVM-Classifier/testimage/%s/%s.jpg' % (formomentLookup1, formomentLookup1))
            os.system(
                'cd YOLOv5 && python detect.py --source ../SVM-Classifier/testimage/%s --weights ../YOLOv5/best.pt --option test --conf 0.25' % (
                    formomentLookup1))

        except Exception as e1:
            print("[조회]yolov5가 작동하지 않아 조회가 되지 않습니다", e1)
            return jsonify({'message': 'fail'})

        try:
            result = getSVMResult(formomentLookup1)
            SVMresult = result.decode('utf-8').split(',')
            if SVMresult == ['']:
                raise Exception('attribute error')
        except Exception as e:
            print("[조회] ML코드가 작동하지 않아 조회가 되지 않습니다", e)
            return jsonify({"message": "fail"})

        print(SVMresult);
        print(SVMresult[1]);

        if SVMresult[1] == '미등록강아지':
            return jsonify({'data': {'isSuccess': False}, 'message': '조회된 강아지가 없습니다'})

        # 조회 성공한 경우
        else:
            try:
                lookupdb = db_connector()
                cursor = lookupdb.cursor()

                foundDog = SVMresult[0]
                accurancy = SVMresult[2]
                accurancy1 = round(float(accurancy), 4) * 100
                accurancy1 = str(accurancy1)
                print(foundDog)
                lookup_sql = "SELECT * FROM pet WHERE uniquenumber='%s'" % (foundDog)
                cursor.execute(lookup_sql)
                dogdata = cursor.fetchall()
                print(dogdata)
                rows = dogdata[0][6]
                petname = dogdata[0][1]
                petbreed = dogdata[0][2]
                petbirth = dogdata[0][3]
                petgender = dogdata[0][4]
                petprofile = dogdata[0][5] + '.jpg'

                # print(rows)
                registerData_sql = "SELECT * FROM registrant WHERE id='%s'" % (rows)
                cursor.execute(registerData_sql)

                datas = cursor.fetchall()
                print(datas)
                registername = datas[0][1]
                registerphone = datas[0][2]
                registeremail = datas[0][3]

                lookupdb.commit()

                return jsonify({'data':
                                    {'registrant': registername, 'phoneNum': registerphone, 'email': registeremail,
                                     'dogRegistNum': foundDog,
                                     'dogName': petname, 'dogBreed': petbreed, 'dogSex': petgender,
                                     'dogBirthYear': petbirth,
                                     'dogProfile': petprofile, 'matchRate': accurancy1, 'isSuccess': True},
                                'message': '조회를 성공했습니다'})

            except Exception as e:
                print("lookupdb에 예외가 발생했습니다")
                return jsonify({'message': 'fail'})

            finally:
                cursor.close()
                lookupdb.close()

@bp.route('/profileImg/<image_file>')
def imgURLConnection(image_file):
    return render_template('profileimage.html',image_file='img/'+image_file)

def createFolder(directory):
    try:
        if not os.path.exists(directory):
            os.makedirs(directory)
    except OSError:
        print('Error: Creating directory. ' + directory)

def uniquenumber(details):
    date_time = datetime.datetime.now()
    for i in range(1):
        a = random.randint(1, 100)
        while a in alist:
            a = random.randint(1, 100)
    alist.append(a)
    #unique = details['dogName'][0] + str(details['phoneNum'][7:11])
    unique = str(details)
    # print(unique)
    reg_num = (str(date_time.year) + str(date_time.month) + str(date_time.day) +str(date_time.second)+ str(a)+unique)
    return reg_num




def getSVMResult(formomentLookup):
    path = 'C:/Users/roger/OneDrive/바탕 화면/pet-connect/AI/nose/SVM-Classifier'
    os.chdir(path)
    cmd =['python','Classifier.py','--test','%s' %(formomentLookup+'.jpg')]
    fd_popen = subprocess.Popen(cmd, stdout=subprocess.PIPE).stdout
    data = fd_popen.read().strip()
    fd_popen.close()
    os.chdir('../')
    return data

def getSVMResultForRegister(formoment):
    path = 'C:/Users/roger/OneDrive/바탕 화면/pet-connect/AI/nose/SVM-Classifier'
    os.chdir(path)
    cmd =['python','Classifier.py','--test','%s' %(formoment+'.jpg')]
    fd_popen = subprocess.Popen(cmd, stdout=subprocess.PIPE,encoding='utf8').stdout
    data = fd_popen.read().strip()
    fd_popen.close()
    os.chdir('../')
    print(" data 는 = " +data)
    return data


def createProfileFolder(directory):
    try:
        if not os.path.exists(directory):
            os.makedirs(directory)
    except OSError:
        print('Error: Creating directory. ' + directory)

#db
def db_connector():
    connector = pymysql.connect(host='localhost',
                                  user='root',
                                  password='Qwer12345678!',
                                  db='pet_connect',
                                  charset='utf8')
    return connector