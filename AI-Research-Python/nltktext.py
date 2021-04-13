# -*- coding: utf-8 -*-
"""
Created on Sat Aug  8 17:50:49 2020

@author: RohitSharma
"""

	


def segment(text, segs):
    words = []
    last = 0
    for i in range(len(segs)):
        if segs[i] == '1':
            words.append(text[last:i+1])
            last=i+1
    words.append(text[last:])
    return words
