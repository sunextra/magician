####一些java tips
*   long和double的赋值与返回操作不是原子操作,因为jvm会将64位的读取分成两个32位的操作来执行
*   i++与i+=2都不是原子操作