# -*- coding: utf-8 -*-
"""
Created on Wed Sep 23 15:56:19 2020

@author: RohitSharma
"""


import yfinance as yf
import pandas as pd
import datetime as dt
import numpy as np

import schedule as sc
import time as t

df=pd.read_csv('ind_nifty200.csv')
cnx_200=df.loc[:,['Yahoo Finance Codes']]


def month(): 
    start = dt.datetime.today()-dt.timedelta(30)
    end = dt.datetime.today()
    writer = pd.ExcelWriter('CNX_200_DATA.xlsx', engine = 'xlsxwriter',date_format='YYYY-MM-DD')
    for cnx in cnx_200:
        column=cnx_200[cnx]
        tickers=column.values
        for ticker in tickers:
            try:
                df1=yf.download(ticker,start,end,interval = "1mo")
                df2=df1.loc[:,['Open','Close']]
                df2.loc[df2['Close'] > df2['Open'], 'OpenIsGreaterThanClose'] = 'TRUE'
                df2.loc[df2['Close'] < df2['Open'], 'OpenIsGreaterThanClose'] = 'FALSE'
                df3= df2.dropna()     
                df3.to_excel(writer, sheet_name = ticker)  
            except:
                print("An exception occurred" + ticker)
    writer.save()
    writer.close()


def macd(macd_fast=12,macd_slow=26,macd_signal=2):
    writer = pd.ExcelWriter('CNX_200_MACD.xlsx', engine = 'xlsxwriter',date_format='YYYY-MM-DD')
    total = pd.DataFrame()
    for cnx in cnx_200:
        column=cnx_200[cnx]
        tickers=column.values
        for ticker in tickers:
            try:
                df = yf.download(ticker,dt.date.today()-dt.timedelta(3650),dt.datetime.today(),interval='1d')
                df['Company']=ticker
                df["MA_Fast"]=df["Close"].ewm(span=12,min_periods=12).mean() 
                df["MA_Slow"]=df["Close"].ewm(span=26,min_periods=26).mean()
                df["MACD"]=df["MA_Fast"]-df["MA_Slow"]
                df["Signal"]=df["MACD"].ewm(span=2,min_periods=2).mean() 
                df.loc[df['MACD'] > df['Signal'], 'MACDIsGreaterThanSIGNALLINE'] = 'TRUE'
                df.loc[df['MACD'] < df['Signal'], 'MACDIsGreaterThanSIGNALLINE'] = 'FALSE'
                df.dropna(inplace=True)
                df2 = df.iloc[:,[6,11]]
               # df2.dropna(inplace=True)   
                df3 = df2.tail(5)
                df3['DateTime']=df3.index
                df4=df3.drop_duplicates()
                df4= df4.reset_index()
                df4 = df4.iloc[:,[1,2,3]]
                df=df4.pivot(index='Company',columns='DateTime',values='MACDIsGreaterThanSIGNALLINE')
                total=total.append(df) 
            except:
                print("An exception occurred" + ticker)
    total.to_excel(writer)        
    writer.save()
    writer.close()
    return total
   
total=macd()    
sc.every().day.at("10:00").do(macd)
while True:
    sc.run_pending()
    t.sleep(1)



from pydrive.auth import GoogleAuth
from pydrive.drive import GoogleDrive
import os
g_login = GoogleAuth()
g_login.LocalWebserverAuth()
drive = GoogleDrive(g_login)









