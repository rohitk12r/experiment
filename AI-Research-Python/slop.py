# -*- coding: utf-8 -*-
"""
Created on Thu Sep 10 12:25:53 2020

@author: RohitSharma
"""
import  pandas_datareader.data as pdr
import numpy as np
import datetime 
import statsmodels.api as sm

ticker = "AAPL"
ohlcv = pdr.get_data_yahoo(ticker, datetime.date.today()-datetime.timedelta(364),datetime.date.today())

ser = ohlcv["Adj Close"]
n = 5
i = 5
def  slope(ser,n):
    slopes = [i^0 for i in range(n-1)]
    for i in range(n, len(ser)+1):
        y = ser[i-n:i]
        x = np.array(range(n))
        y_scaled = (y - y.min())/(y.max()-y.min())
        x_scaled = (x - x.min())/(x.max()-x.min())
        x_scaled = sm.add_constant(x_scaled)
        model = sm.OLS(y_scaled, x_scaled)
        results = model.fit()
        slopes.append(results.params[-1])
    slope_angel = (np.rad2deg(np.arctan(np.array(slopes))))
    return np.array(slope_angel)


df = ohlcv.copy()    
df["slope"] = slope(ohlcv["Adj Close"],5)

df.iloc[:,[4,6]].plot()
    

