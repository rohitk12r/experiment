eq# -*- coding: utf-8 -*-
"""
Created on Wed Sep 23 18:41:15 2020

@author: RohitSharma
"""


import requests
from bs4 import BeautifulSoup
import pandas as pd

financial_dir = {}
temp_dir = {}
url = 'https://finance.yahoo.com/quote/BHEL.NS/history?p=BHEL.NS'
page = requests.get(url)
page_content = page.content
soup = BeautifulSoup(page_content,'html.parser')
tabl = soup.find_all("table", {"class" : "W(100%) M(0)"})
for t in tabl:
     rows = t.find_all("tr")
     for row in rows:
        if len(row.get_text(separator='|').split("|")[0:2])>1:
            temp_dir[row.get_text(separator='|').split("|")[0]]=row.get_text(separator='|').split("|")[1]   
   
financial_dir["BHEL.NS"] = temp_dir
