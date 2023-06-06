from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name="home"),
    path('signup/', views.SignUp.as_view(), name="signup"),
    path('room/', views.room_list, name='room_list'),
    path('<slug:category_slug>/', views.room_list, name='room_list_by_category'),
    path('<int:id>/<slug:slug>/', views.room_detail, name="room_detail")
]