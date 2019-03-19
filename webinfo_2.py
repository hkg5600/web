import requests
from bs4 import BeautifulSoup

req  = requests.get("http://www.dgsw.hs.kr/user/carte/list.do?menuCd=MCD_000000000000029947")

html = req.text

soup = BeautifulSoup(html, "html.parser")
'''a = soup.select(
    '.meals_today_list'
    )
'''
a = soup.select(
    'ul.meals_today_list > li'
    )
for info in a:
    
    print(info.text.strip())