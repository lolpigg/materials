from urllib import request
from bs4 import BeautifulSoup
response = request.urlopen("http://example.org")
html = response.read()
soup = BeautifulSoup(html, "html.parser")
title_tag = soup.title
if title_tag is not None:
    title_text = title_tag.string
    print("Значение из тега <title>: ", title_text)
else:
    print("Тег <title> не найден на странице")
