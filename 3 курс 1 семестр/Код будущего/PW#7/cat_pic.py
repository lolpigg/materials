from urllib import request
from PIL import Image
from io import BytesIO

def get_cat_image(tag=None):
    url = "https://cataas.com/cat"
    if tag:
        url += f"/{tag}"
    with request.urlopen(url) as response:
        img_data = response.read()
        return img_data

def display_image(img_data):
    img = Image.open(BytesIO(img_data))
    img.show()

def main():
    tag = input("Введите тег для изображения (или оставьте пустым для случайного изображения): ")
    cat_image_data = get_cat_image(tag)
    display_image(cat_image_data)

if __name__ == "__main__":
    main()
