"""
URL configuration for firstproject project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path,include
from hello.views import *

urlpatterns = [
    path('admin/', admin.site.urls),
    path('contacti.html',contacti),
    path('',main_menu),
    path('main_menu.html',main_menu),
    path('arenda.html',arenda),
    path('o_nas.html',o_nas),
    path('pravila.html',pravila),
    path('create/', create_view),
    path('update/<int:id>', update_view),
    path('accounts/',include('django.contrib.auth.urls')),
    path('home/', include('hello.urls')),
    path('<int:id>/', comment_detail_view),
    path('delete/<int:id>/', delete_view)
]
