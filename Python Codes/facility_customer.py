import random


noOfFacility =  noOFCustomer = 30
file = open(r".\FC\fc_"+str(noOfFacility)+".txt","w")
for ii in range(noOfFacility*2):
    arr = [None] * noOfFacility
    j = 0
    for i in range(noOfFacility):
        while(True):
            temp = random.randint(1,noOfFacility*2)
            flag = 0
            for k in range(j):
                if(temp == arr[k]):
                    flag = 1
                    break
            if flag == 0:
                break
        arr[j] = temp
        file.write(str(arr[j])+" ")
        j = j + 1
    
    file.write("\n")
    j = 0
    for i in range(noOfFacility):
        while(True):
            temp = random.randint(1,20)
            flag = 0
            for k in range(j):
                if(temp == arr[k]):
                    flag = 1
            if flag == 0:
                break;
        arr[j] = temp
        file.write(str(arr[j])+" ")
        j = j + 1

    file.write("\n")
    file.write("\n")