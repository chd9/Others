#!/usr/bin/python2.7.16
# -*- coding: utf-8 -*-
"""
    @Time    : 2020/5/19 18:29
    @Author  : donnie99
    @Email   : chd9_com@163.com
    @File    : server.py
    @Software: PyCharm
"""
import sys

sys.path.append('./gen-py')

from chd9.server.mytest import MyTest
from mytest.ttypes import *

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import socket


class MyTestHandler:
    def __init__(self):
        self.log = {}

    def helloThrift(self):
        print "Hello Thrift!"

    def studyThrift(self):
        print "I'm studying Thrift!"
        return "I'm studying Thrift from " + socket.gethostbyname(socket.gethostname())

    def sayMsg(self, msg):
        print "sayMsg(" + msg + ")"
        return "say " + msg + " from " + socket.gethostbyname(socket.gethostname())


handler = MyTestHandler()
processor = MyTest.Processor(handler)
transport = TSocket.TServerSocket('127.0.0.1', 3333)
# 选择传输层
tfactory = TTransport.TBufferedTransportFactory()
# 选择传输协议
pfactory = TBinaryProtocol.TBinaryProtocolFactory()
# 创建服务端
server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

print "Starting python server..."
server.serve()