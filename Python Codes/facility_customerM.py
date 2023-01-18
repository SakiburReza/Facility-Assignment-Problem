import random

file = open(r".\FC\fc_100.txt","r")
file2 = open(r".\FC\fcM_100.txt","w")
for i, line in enumerate(file):
    if i%3 == 0:
        file2.write(line)
        totalCustomer = 0
        for i in range(10):
            capacity = random.randint(1,5)
            totalCustomer += capacity
            file2.write(str(capacity)+" ")
        file2.write("\n")
        for i in range(totalCustomer):
            cp = random.randint(1,20)
            file2.write(str(cp)+" ")
        file2.write("\n\n")
   