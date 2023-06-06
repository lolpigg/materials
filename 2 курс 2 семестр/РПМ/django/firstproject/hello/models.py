from django.db import models
from django.urls import *
# Create your models here.
class Person(models.Model):
        name = models.CharField(max_length=20)
        lastname = models.CharField(max_length=20)

class Log(models.Model):
        login = models.CharField(max_length=30)
        password = models.CharField(max_length=40)
        person = models.OneToOneField(Person, on_delete = models.CASCADE, primary_key = True)

class Category(models.Model):
        name = models.CharField(max_length=30, db_index=True)
        slug = models.SlugField(max_length=30, db_index=True, unique=True)
        class Meta:
                ordering = ('name',)
                verbose_name = 'Категория'
                verbose_name_plural = 'Категории'

        def __str__(self):
            return self.name
        
        def get_absolute_url(self):
                return reverse('room_list_by_category', args=[self.slug])

class Room(models.Model):
        id = models.BigAutoField(primary_key=True)
        name = models.CharField(max_length=30, db_index=True)
        slug = models.SlugField(max_length=30, db_index=True)
        href_img = models.ImageField(upload_to='rooms/%Y/%m/%d', blank=True)
        workdays_price = models.DecimalField(max_digits=15, decimal_places=2)
        price = models.DecimalField(max_digits=15, decimal_places=2)
        category = models.ForeignKey(Category, related_name='rooms', on_delete=models.CASCADE)
        stock = models.PositiveIntegerField()
        available = models.BooleanField(default=True)
        created = models.DateTimeField(auto_now_add=True)
        updated = models.DateTimeField(auto_now=True)
        class Meta:
               ordering=('name',)
               index_together = (('id', 'slug'))
        def __str__(self):
            return self.name
        def get_absolute_url(self):
            return reverse("room_detail", args=[self.id, self.slug])
        

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