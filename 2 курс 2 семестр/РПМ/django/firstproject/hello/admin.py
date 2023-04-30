from django.contrib import admin
from .models import Person
from .models import Logs
from .models import Room
from .models import Social_Media
from .models import Events
# Register your models here.
class PersonAdmin(admin.ModelAdmin):
    list_display = ('name', 'lastname')

class LogsAdmin(admin.ModelAdmin):
    list_display = ('login', 'password', 'person')

class RoomAdmin(admin.ModelAdmin):
    list_display = ('name', 'workdays_price', 'weekends_price')

class Social_MediaAdmin(admin.ModelAdmin):
    list_display = ('name', 'href')

class EventsAdmin(admin.ModelAdmin):
    list_display = ('name', 'time', 'description')

admin.site.register(Person,PersonAdmin)
admin.site.register(Logs,LogsAdmin)
admin.site.register(Room,RoomAdmin)
admin.site.register(Social_Media,Social_MediaAdmin)
admin.site.register(Events,EventsAdmin)