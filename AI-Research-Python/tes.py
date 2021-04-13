# -*- coding: utf-8 -*-
"""
Created on Tue Sep 15 09:35:34 2020

@author: RohitSharma
"""

import pandas as pd

dict = {
        'apples' : [3,5,3,2],
        'bananan' : [3,2,2,1]
        }

purchase = pd.DataFrame(dict, index=['rohit','ronit','neeraj','sharma'])
purchase.loc['rohit']
