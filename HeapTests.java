package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class HeapTests {

    //heapify
    
    @Test
    void un_elemento() {
        ComparadorAntiguedad c = new ComparadorAntiguedad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(c);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado e = new Traslado(6, 1, 0, 1000, 41);
        test.add(e);

        heap.heapify(test);

        assertEquals(test, heap.array());
        assertEquals(1, heap.longitud());
    }


    @Test
    void dos_elementos() {
        ComparadorAntiguedad c = new ComparadorAntiguedad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(c);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado a = new Traslado(6, 1, 0, 1000, 41);
        Traslado b = new Traslado(2, 1, 5, 63, 10);
        test.add(a);
        test.add(b);
       
        heap.heapify(test);


        ArrayList<Traslado> res = new ArrayList<>();
        res.add(b);
        res.add(a);


        assertEquals(res, heap.array());
        assertEquals(2, heap.longitud());
    }


    @Test
    void vacío(){
        ComparadorAntiguedad c = new ComparadorAntiguedad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(c);

        ArrayList<Traslado> test = new ArrayList<>();
       
        heap.heapify(test);

        assertEquals(test, heap.array());
        assertEquals(0, heap.longitud());
    }


    @Test
    void ultimo_y_maximo(){
        ComparadorAntiguedad com = new ComparadorAntiguedad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado a = new Traslado(6, 1, 0, 1000, 41);
        Traslado b = new Traslado(2, 1, 5, 63, 10);
        Traslado c = new Traslado(1, 4, 2, 63, 13);
        Traslado d = new Traslado(5, 1, 5, 63, 3);
        Traslado e = new Traslado(7, 1, 5, 63, 16);
        Traslado f = new Traslado(8, 1, 5, 63, 7);
        Traslado g = new Traslado(9, 1, 5, 63, 1);
        Traslado h = new Traslado(15, 1, 5, 63, 33);


        test.add(a);
        test.add(b);
        test.add(c);
        test.add(d);
        test.add(e);
        test.add(f);
        test.add(g);
        test.add(h);
       
        heap.heapify(test);


        ArrayList<Traslado> res = new ArrayList<>();
        res.add(g);
        res.add(d);
        res.add(f);
        res.add(b);
        res.add(e);
        res.add(a);
        res.add(c);
        res.add(h);

        assertEquals(res, heap.array());
        assertEquals(8, heap.longitud());
        assertEquals(h, heap.obtener(7));
        assertEquals(g, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));
    }


    @Test

    void peor_caso_heapify(){
        ComparadorAntiguedad com = new ComparadorAntiguedad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado a = new Traslado(6, 1, 0, 1000, 10);
        Traslado b = new Traslado(2, 1, 5, 63, 9);
        Traslado c = new Traslado(1, 4, 2, 63, 8);
        Traslado d = new Traslado(5, 1, 5, 63, 7);
        Traslado e = new Traslado(7, 1, 5, 63, 6);
        Traslado f = new Traslado(8, 1, 5, 63, 5);
        Traslado g = new Traslado(9, 1, 5, 63, 4);
        Traslado h = new Traslado(15, 1, 5, 63, 3);
        Traslado i = new Traslado(15, 1, 5, 63, 2);
        Traslado j = new Traslado(15, 1, 5, 63, 1);

        test.add(a);
        test.add(b);
        test.add(c);
        test.add(d);
        test.add(e);
        test.add(f);
        test.add(g);
        test.add(h);
        test.add(i);
        test.add(j);

       
        heap.heapify(test);


        ArrayList<Traslado> res = new ArrayList<>();
        res.add(j);
        res.add(i);
        res.add(g);
        res.add(h);
        res.add(e);
        res.add(f);
        res.add(c);
        res.add(a);
        res.add(d);
        res.add(b);


        assertEquals(res, heap.array());
        assertEquals(10, heap.longitud());
        assertEquals(b, heap.obtener(9));
        assertEquals(j, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));
    }



    @Test

    void re_heapify(){
        ComparadorAntiguedad com = new ComparadorAntiguedad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado a = new Traslado(6, 1, 0, 1000, 10);
        Traslado b = new Traslado(2, 1, 5, 63, 9);
        Traslado c = new Traslado(1, 4, 2, 63, 8);
        Traslado d = new Traslado(5, 1, 5, 63, 7);
        Traslado e = new Traslado(7, 1, 5, 63, 6);
        Traslado f = new Traslado(8, 1, 5, 63, 5);
        Traslado g = new Traslado(9, 1, 5, 63, 4);
        Traslado h = new Traslado(15, 1, 5, 63, 3);
        Traslado i = new Traslado(15, 1, 5, 63, 2);
        Traslado j = new Traslado(15, 1, 5, 63, 1);

        test.add(a);
        test.add(b);
        test.add(c);
        test.add(d);
        test.add(e);
        test.add(f);
        test.add(g);
        test.add(h);
        test.add(i);
        test.add(j);

       
        heap.heapify(test);
        heap.heapify(heap.array());
        heap.heapify(heap.array());
        heap.heapify(heap.array());



        ArrayList<Traslado> res = new ArrayList<>();
        res.add(j);
        res.add(i);
        res.add(g);
        res.add(h);
        res.add(e);
        res.add(f);
        res.add(c);
        res.add(a);
        res.add(d);
        res.add(b);


        assertEquals(res, heap.array());
        assertEquals(10, heap.longitud());
        assertEquals(b, heap.obtener(9));
        assertEquals(j, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));
    }


    
    //insertar

    @Test

    void meto_uno(){
        ComparadorRedituabilidad com = new ComparadorRedituabilidad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado a = new Traslado(6, 1, 0, 1000, 41);
        test.add(a);
       
        heap.insertar(a);

        assertEquals(test, heap.array());
        assertEquals(1, heap.longitud());
        assertEquals(a, heap.obtener(0));
        assertEquals(heap.maximo(), heap.obtener(0));
    }

    @Test
    
    void meto_varios(){
        ComparadorRedituabilidad com = new ComparadorRedituabilidad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);


        Traslado a = new Traslado(6, 1, 0, 1000, 41);
        Traslado b = new Traslado(2, 1, 5, 444, 10);
        Traslado c = new Traslado(1, 4, 2, 63, 13);
        Traslado d = new Traslado(5, 1, 5, 100, 3);
        Traslado e = new Traslado(7, 1, 5, 5, 16);
        Traslado f = new Traslado(8, 1, 5, 500, 7);
        Traslado g = new Traslado(9, 1, 5, 365, 1);
        Traslado h = new Traslado(15, 1, 5, 1200, 33);

        ArrayList<Traslado> intermedio = new ArrayList<>();
        intermedio.add(a);
        intermedio.add(b);
        intermedio.add(f);
        intermedio.add(d);
        intermedio.add(e);
        intermedio.add(c);

        
        heap.insertar(a);
        heap.insertar(b);
        heap.insertar(c);
        heap.insertar(d);
        heap.insertar(e);
        heap.insertar(f);

        assertEquals(intermedio, heap.array());
        assertEquals(6, heap.longitud());
        assertEquals(a, heap.maximo());
        assertEquals(c, heap.obtener(5));


        ArrayList<Traslado> res = new ArrayList<>();
        res.add(h);
        res.add(a);
        res.add(f);
        res.add(b);
        res.add(e);
        res.add(c);
        res.add(g);
        res.add(d);


        heap.insertar(g);
        heap.insertar(h);


        assertEquals(res, heap.array());
        assertEquals(8, heap.longitud());

        assertEquals(d, heap.obtener(7));
        assertEquals(h, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));
    }


    @Test

    void peor_caso(){
        ComparadorRedituabilidad com = new ComparadorRedituabilidad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);


        Traslado a = new Traslado(6, 1, 0, 10, 41);
        Traslado b = new Traslado(2, 1, 5, 20, 10);
        Traslado c = new Traslado(1, 4, 2, 30, 13);
        Traslado d = new Traslado(5, 1, 5, 40, 3);
        Traslado e = new Traslado(7, 1, 5, 50, 16);
        Traslado f = new Traslado(8, 1, 5, 60, 7);
        Traslado g = new Traslado(9, 1, 5, 70, 1);
        Traslado h = new Traslado(15, 1, 5, 80, 33);
        Traslado i = new Traslado(15, 1, 5, 90, 33);
        Traslado j = new Traslado(15, 1, 5, 100, 33);


        ArrayList<Traslado> intermedio = new ArrayList<>();
        intermedio.add(e);
        intermedio.add(d);
        intermedio.add(b);
        intermedio.add(a);
        intermedio.add(c);
        

        heap.insertar(a);
        heap.insertar(b);
        heap.insertar(c);
        heap.insertar(d);
        heap.insertar(e);

        assertEquals(intermedio, heap.array());
        assertEquals(5, heap.longitud());
        assertEquals(e, heap.maximo());
        assertEquals(c, heap.obtener(4));


        ArrayList<Traslado> res = new ArrayList<>();
        res.add(j);
        res.add(i);
        res.add(f);
        res.add(g);
        res.add(h);
        res.add(b);
        res.add(e);
        res.add(a);
        res.add(d);
        res.add(c);


        heap.insertar(f);
        heap.insertar(g);
        heap.insertar(h);
        heap.insertar(i);
        heap.insertar(j);



        assertEquals(res, heap.array());
        assertEquals(10, heap.longitud());

        assertEquals(c, heap.obtener(9));
        assertEquals(j, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));
    }
    
    
    //eliminarElemento


    @Test

    void eliminar_unico(){
        ComparadorRedituabilidad com = new ComparadorRedituabilidad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);

        ArrayList<Traslado> test = new ArrayList<>();
        Traslado a = new Traslado(6, 1, 0, 1000, 41);
       
        heap.insertar(a);
        heap.eliminarElemento(0);

        assertEquals(test, heap.array());
        assertEquals(0, heap.longitud());
    }


    @Test

    void eliminar_varios(){
        ComparadorRedituabilidad com = new ComparadorRedituabilidad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);


        Traslado a = new Traslado(6, 1, 0, 10, 41);
        Traslado b = new Traslado(2, 1, 5, 20, 10);
        Traslado c = new Traslado(1, 4, 2, 30, 13);
        Traslado d = new Traslado(5, 1, 5, 40, 3);
        Traslado e = new Traslado(7, 1, 5, 50, 16);
        Traslado f = new Traslado(8, 1, 5, 60, 7);
        Traslado g = new Traslado(9, 1, 5, 70, 1);
        Traslado h = new Traslado(15, 1, 5, 80, 33);
        Traslado i = new Traslado(15, 1, 5, 90, 33);
        Traslado j = new Traslado(15, 1, 5, 100, 33);

        heap.insertar(a);
        heap.insertar(b);
        heap.insertar(c);
        heap.insertar(d);
        heap.insertar(e);
        heap.insertar(f);
        heap.insertar(g);
        heap.insertar(h);
        heap.insertar(i);
        heap.insertar(j);

        heap.eliminarElemento(0);
        heap.eliminarElemento(0);


        ArrayList<Traslado> intermedio = new ArrayList<>();
        intermedio.add(h);
        intermedio.add(g);
        intermedio.add(f);
        intermedio.add(d);
        intermedio.add(c);
        intermedio.add(b);
        intermedio.add(e);
        intermedio.add(a);


        assertEquals(intermedio, heap.array());
        assertEquals(8, heap.longitud());
        assertEquals(a, heap.obtener(7));
        assertEquals(h, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));


        heap.eliminarElemento(1);
        heap.eliminarElemento(2);


        ArrayList<Traslado> intermedio2 = new ArrayList<>();
        intermedio2.add(h);
        intermedio2.add(d);
        intermedio2.add(e);
        intermedio2.add(a);
        intermedio2.add(c);
        intermedio2.add(b);


        assertEquals(intermedio2, heap.array());
        assertEquals(6, heap.longitud());
        assertEquals(b, heap.obtener(5));
        assertEquals(h, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));


        heap.eliminarElemento(0);
        heap.eliminarElemento(0);
        heap.eliminarElemento(0);
        heap.eliminarElemento(0);
        heap.eliminarElemento(0);
        heap.eliminarElemento(0);

        ArrayList<Traslado> res = new ArrayList<>();

        assertEquals(res, heap.array());
        assertEquals(0, heap.longitud());
    }


    @Test
    void eliminar_hojas(){
        ComparadorRedituabilidad com = new ComparadorRedituabilidad();
        maxHeap<Traslado> heap = new maxHeap<Traslado>(com);


        Traslado a = new Traslado(6, 1, 0, 10, 41);
        Traslado b = new Traslado(2, 1, 5, 20, 10);
        Traslado c = new Traslado(1, 4, 2, 30, 13);
        Traslado d = new Traslado(5, 1, 5, 40, 3);
        Traslado e = new Traslado(7, 1, 5, 50, 16);
        Traslado f = new Traslado(8, 1, 5, 60, 7);
        Traslado g = new Traslado(9, 1, 5, 70, 1);
        Traslado h = new Traslado(15, 1, 5, 80, 33);
        Traslado i = new Traslado(15, 1, 5, 90, 33);
        Traslado j = new Traslado(15, 1, 5, 100, 33);

        heap.insertar(a);
        heap.insertar(b);
        heap.insertar(c);
        heap.insertar(d);
        heap.insertar(e);
        heap.insertar(f);
        heap.insertar(g);
        heap.insertar(h);
        heap.insertar(i);
        heap.insertar(j);

        heap.eliminarElemento(9);
        heap.eliminarElemento(8);


        ArrayList<Traslado> intermedio = new ArrayList<>();
        intermedio.add(j);
        intermedio.add(i);
        intermedio.add(f);
        intermedio.add(g);
        intermedio.add(h);
        intermedio.add(b);
        intermedio.add(e);
        intermedio.add(a);


        assertEquals(intermedio, heap.array());
        assertEquals(8, heap.longitud());
        assertEquals(a, heap.obtener(7));
        assertEquals(j, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));


        heap.eliminarElemento(7);
        heap.eliminarElemento(6);
        heap.eliminarElemento(5);
        heap.eliminarElemento(4);

        ArrayList<Traslado> intermedio2 = new ArrayList<>();
        intermedio2.add(j);
        intermedio2.add(i);
        intermedio2.add(f);
        intermedio2.add(g);



        assertEquals(intermedio2, heap.array());
        assertEquals(4, heap.longitud());
        assertEquals(g, heap.obtener(3));
        assertEquals(j, heap.maximo());
        assertEquals(heap.maximo(), heap.obtener(0));


        heap.eliminarElemento(3);
        heap.eliminarElemento(2);
        heap.eliminarElemento(1);
        heap.eliminarElemento(0);

        ArrayList<Traslado> res = new ArrayList<>();

        assertEquals(res, heap.array());
        assertEquals(0, heap.longitud());
    }
}


