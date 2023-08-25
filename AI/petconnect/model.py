from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

#regname,regphone,regemail
#petname,petbreed,petbirth,petgender,petprofile,reg_id,uniquenumber

# 등록자
class Registrant(db.Model):
    id=db.Column(db.Integer,primary_key=True)
    regName=db.Column(db.String(200),nullable=False)
    regPhone=db.Column(db.String(200),nullable=False)
    regEmail=db.Column(db.String(200),nullable=False)

# 반려견 정보
class Pet(db.Model):
    id=db.Column(db.Integer,primary_key=True)
    petName=db.Column(db.String(200),nullable=False)
    petBreed=db.Column(db.String(200),nullable=False)
    petGender=db.Column(db.String(200),nullable=False)
    petBirth=db.Column(db.String(200),nullable=False)
    petProfile=db.Column(db.String(400),nullable=False)
    reg_Id=db.Column(db.Integer,db.ForeignKey('registrant.id',ondelete='CASCADE'))
    registNumber=db.Column(db.Interger, nullable=False)
    registrant=db.relationship('Registrant',backref=db.backref('pet_set'))
    uniqueNumber=db.Column(db.String(200),nullable=False)