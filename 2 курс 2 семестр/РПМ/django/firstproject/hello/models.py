from django.db import models

# Create your models here.
class Person(models.Model):
        name = models.CharField(max_length=20)
        lastname = models.CharField(max_length=20)

class Log(models.Model):
        login = models.CharField(max_length=30)
        password = models.CharField(max_length=40)
        person = models.OneToOneField(Person, on_delete = models.CASCADE, primary_key = True)

class Room(models.Model):
        id = models.BigAutoField(primary_key=True)
        name = models.CharField(max_length=30)
        href_img = models.CharField(max_length=300)
        workdays_price = models.IntegerField()
        weekends_price = models.IntegerField()

class Social_Media(models.Model):
        id = models.BigAutoField(primary_key=True)
        name = models.CharField(max_length=30)
        href = models.CharField(max_length=300)
        href_img = models.CharField(max_length=300)

#?
class Event(models.Model):
        name = models.CharField(max_length=60)
        time = models.DateTimeField()
        description = models.CharField(max_length=300)

class Contact(models.Model):
        id = models.BigAutoField(primary_key=True)
        name = models.CharField(max_length=20)
        contact = models.CharField(max_length=100)
        Types = ('Tel', 'Телефон'),('Mail', 'Электронная почта'),('Href', 'Обычная ссылка'),('None', 'Без ссылки')
        type = models.CharField(max_length=5, choices=Types)

class Price(models.Model):
        id = models.BigAutoField(primary_key=True)
        text = models.CharField(max_length=30)
        price = models.IntegerField()
        stopcheck = models.BooleanField()

class Manager(models.Model):
        id = models.BigAutoField(primary_key=True)
        name = models.CharField(max_length=20)
        profession = models.CharField(max_length=20)
        telephone = models.CharField(max_length=15)
        href_img = models.CharField(max_length=300)

class Comment(models.Model):
        id = models.BigAutoField(primary_key=True)
        name = models.CharField(max_length=20)
        date = models.DateField()
        text = models.CharField(max_length=500)
        def __str__(self):
                return f'{self.name, self.date, self.text}'