from django.shortcuts import render
from cart.cart import Cart
from .forms import *
from .models import *
import sqlite3
from django.core.mail import send_mail
from django.conf import settings

def order_create(request):
    cart = Cart(request)
    if request.method == 'POST':
        form = OrderCreateForm(request.POST)
        if form.is_valid():
            order=form.save()
            for item in cart:
                OrderItem.objects.create(order=order, product=item['product'],price=item['price'],quantity=item['quantity'])
            cart.clear()                 
            return render (request, 'orders/created.html', {'order': order})
    else:
        form = OrderCreateForm()
    return render(request, 'orders/create.html',{'cart':cart, 'form':form})
def send_order(request):
    conn = sqlite3.connect('db.sqlite3')
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM \"orders_orderitem\"")
    rows = cursor.fetchall()
    subject = 'Заказ на сайте Кириешки'
    cursor.execute(f"SELECT * FROM \"orders_order\" WHERE id={rows[-1][3]}")
    rows2 = cursor.fetchall()
    order = rows2[0]
    cursor.execute(f"SELECT * FROM \"hello_room\" WHERE id={rows[-1][4]}")
    rows3 = cursor.fetchall()
    room = rows3[0]
    message = ''
    message += f"Здравствуйте, {order[1]} {order[2]}! Спасибо за заказ на нашем сайте! \nВаш заказ:\nНомер заказа: {rows[-1][0]}, Цена: {rows[-1][1]}, Номер товара: {rows[-1][4]}\nВы заказали: {room[1]} на {rows[-1][2]} часов\n"
    conn.close()
    send_mail(subject, message, settings.EMAIL_HOST_USER, ["nidhappy@mail.ru"])
    return render(request, 'orders/test.html')