#!/bin/bash

rm -f /home/xiaohao/github/BestAPI/BestAPI/shell/mapreduce.log
touch /home/xiaohao/github/BestAPI/BestAPI/shell/mapreduce.log
/home/xiaohao/github/BestAPI/BestAPI/shell/predict.sh >> /home/xiaohao/github/BestAPI/BestAPI/shell/mapreduce.log 2>&1
