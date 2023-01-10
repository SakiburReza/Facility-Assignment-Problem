import networkx as nx
import random
import sys
import math

file = open(".\Graphs\graph_200"+".txt","w")
for ii in range(20):
    param1 = random.randint(5,100)/100
    nodes = 200
    avg_deg = 0
    param2= 0
    class_of_graph = 1
    while True:
        if class_of_graph==0:
            #graph = nx.connected_watts_strogatz_graph(nodes,avg_deg,.2)
            graph = nx.connected_watts_strogatz_graph(nodes,int(avg_deg),param2)
        elif class_of_graph==1:
            graph = nx.generators.random_graphs.erdos_renyi_graph(nodes,param1)
            print("graph")
        elif class_of_graph==2:
            graph = nx.generators.random_graphs.barabasi_albert_graph(nodes,int(param1))
        elif class_of_graph==3:
            graph = nx.generators.geometric.random_geometric_graph(nodes,param1)
        if nx.is_connected(graph):
            break

    edges = graph.edges()
    edge_weights = []
    print(ii)
    print(param1)
    file.write(str(ii+1)+"\n")
    file.write(str(param1)+"\n")
    file.write(str(nodes)+"\n")
    file.write(str(graph.number_of_edges())+"\n")
   
    edges = graph.edges()
    j = 0
    for e in edges:
        edge_weights.append(random.randint(1,100))
        file.write(str(e[0]+1)+" "+str(e[1]+1)+" "+str(edge_weights[j])+"\n")
        j = j+1
    file.write("\n")
file.close()


        