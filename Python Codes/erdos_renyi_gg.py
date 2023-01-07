import networkx as nx
import random
import sys
import math
for ii in range(1):
    param1 = random.random()
    nodes = 20
    avg_deg = 0
    param2= 0
    class_of_graph = 3
    while True:
        if class_of_graph==0:
            #graph = nx.connected_watts_strogatz_graph(nodes,avg_deg,.2)
            graph = nx.connected_watts_strogatz_graph(nodes,int(avg_deg),param2)
        elif class_of_graph==1:
            graph = nx.generators.random_graphs.erdos_renyi_graph(nodes,param1)
        elif class_of_graph==2:
            graph = nx.generators.random_graphs.barabasi_albert_graph(nodes,int(param1))
        elif class_of_graph==3:
            graph = nx.generators.geometric.random_geometric_graph(nodes,param1)
        if nx.is_connected(graph):
            break

    if class_of_graph==3:
        pos=nx.get_node_attributes(graph,'pos')
    edges = graph.edges()
    edge_weights = []
    print(ii)
    file = open(".\Graphs\graph_20_"+str(round(param1,4))+".txt","w")
    file.write(str(nodes)+"\n")
    file.write(str(graph.number_of_edges())+"\n")
    if class_of_graph==3:
        pos=nx.get_node_attributes(graph,'pos')
    edges = graph.edges()
    j = 0
    for e in edges:
        edge_weights.append(random.randint(1,100))
        file.write(str(e[0]+1)+" "+str(e[1]+1)+" "+str(edge_weights[j])+"\n")
        j = j+1
    file.close()
        