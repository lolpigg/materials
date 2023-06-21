from django.contrib import admin
from .models import *
# Register your models here.
class PersonAdmin(admin.ModelAdmin):
    list_display = ('name', 'lastname')

class LogAdmin(admin.ModelAdmin):
    list_display = ('login', 'password', 'person')

class RoomAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'slug','href_img', 'workdays_price', 'price', 'available', 'created', 'updated')
    list_filter = ['available', 'created', 'updated']
    list_editable = ['workdays_price', 'price', 'available']
    prepopulated_fields = {'slug': ('name',)}

class Social_MediaAdmin(admin.ModelAdmin):
    list_display = ('id','name', 'href','href_img')
    
class EventAdmin(admin.ModelAdmin):
    list_display = ('name', 'time', 'description')
    
class ContactAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'contact', 'type')
    
class PriceAdmin(admin.ModelAdmin):
    list_display = ('id', 'text', 'price', 'stopcheck')

class ManagerAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'profession', 'telephone')

class CommentAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'date', 'text')

class CategoryAdmin(admin.ModelAdmin):
    list_display = ('name', 'slug')
    prepopulated_fields = {'slug' : ('name',)}

admin.site.register(Person, PersonAdmin)
admin.site.register(Log, LogAdmin)
admin.site.register(Social_Media, Social_MediaAdmin)
admin.site.register(Event, EventAdmin)
admin.site.register(Contact, ContactAdmin)
admin.site.register(Price, PriceAdmin)
admin.site.register(Comment, CommentAdmin)
admin.site.register(Room, RoomAdmin)
admin.site.register(Category, CategoryAdmin)
admin.site.register(Manager, ManagerAdmin)