from django.db import models

# Create your models here.
class Person(models.Model):
        name = models.CharField(max_length=20)
        lastname = models.CharField(max_length=20)

class Logs(models.Model):
        login = models.CharField(max_length=30)
        password = models.CharField(max_length=40)
        person = models.OneToOneField(Person, on_delete = models.CASCADE, primary_key = True)

class Room(models.Model):
        name = models.CharField(max_length=30)
        workdays_price = models.IntegerField()
        weekends_price = models.IntegerField()

class Social_Media(models.Model):
        name = models.CharField(max_length=30)
        href = models.CharField(max_length=40)

class Events(models.Model):
        name = models.CharField(max_length=60)
        time = models.DateTimeField()
        description = models.CharField(max_length=300)