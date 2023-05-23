from django.shortcuts import *
from django.urls import reverse_lazy
from django.http import *
from django.contrib.auth.forms import UserCreationForm
from django.views.generic.edit import CreateView
from .models import *
from .forms import *

# Create your views here.
def main_menu(request):
    comments = Comment.objects.order_by('date')
    context = {'comments':comments}
    return render(request,'hello/main_menu.html', context)
def arenda(request):
    rooms = Room.objects.order_by('id')
    managers = Manager.objects.order_by('id')
    context = {'rooms':rooms, 'managers':managers}
    return render(request,'hello/arenda.html', context)
def contacti(request):
    contacts = Contact.objects.order_by('id')
    social_medias = Social_Media.objects.order_by('id')
    context = {'contacts':contacts, 'social_medias':social_medias}
    return render(request,'hello/contacti.html', context)
def o_nas(request):
    prices = Price.objects.order_by('id')
    context = {'prices':prices}
    return render(request,'hello/o_nas.html',context)
def pravila(request):
    return render(request,'hello/pravila.html')
def home(request):
    return render(request, 'hello/home.html')

def create_view(request):
    if request.method == 'POST':
        form = CommentForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('/')
    else:
        form = CommentForm()
        comments = Comment.objects.order_by('id')
        context = {
            'form':form,
            'comments':comments
        }
        return render(request, 'hello/create.html', context)

def comment_detail_view(request, id):
    try:
        data = Comment.objects.get(id=id)
    except CommentForm.DoesNotExist:
        raise Http404('Такого не существует')
    return render(request, 'hello/detailview.html', {'data':data})

def update_view(request, id):
    try:
        old_data = get_object_or_404(Comment, id=id)
    except CommentForm.DoesNotExist:
        raise Http404('Такого не существует')

    if request.method == 'POST':
        form = CommentForm(request.POST, instance = old_data)
        if form.is_valid():
            form.save()
            return redirect(f'/{id}')
    else:
        form = CommentForm(instance = old_data)
        context = {
            'form':form
        }
        return render(request, 'hello/update.html', context)
    
def delete_view(request, id):
    try:
        data = get_object_or_404(Comment, id=id)
    except CommentForm.DoesNotExist:
        raise Http404('Такого не существует')

    if request.method == 'POST':
        data.delete()
        return redirect('/')
    else:
        return render(request, 'hello/delete.html')
class SignUp(CreateView):
    form_class = UserCreationForm
    success_url = reverse_lazy('login')
    template_name = 'registration/signup.html'