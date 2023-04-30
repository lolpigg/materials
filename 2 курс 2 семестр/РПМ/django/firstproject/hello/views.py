from django.shortcuts import render

# Create your views here.
def main_menu(request):
    return render(request,'hello/main_menu.html')
def arenda(request):
    return render(request,'hello/arenda.html')
def contacti(request):
    return render(request,'hello/contacti.html')
def o_nas(request):
    return render(request,'hello/o_nas.html')
def pravila(request):
    return render(request,'hello/pravila.html')