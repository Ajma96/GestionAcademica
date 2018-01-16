/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Creamos el objeto asignatura y todos sus métodos.
 */

$.asignatura = {};

// Configuración del HOST y URL del servicio
$.asignatura.HOST = 'http://localhost:8080';
$.asignatura.URL = '/GestionAcademica/rest/asignatura';

$.asignatura.panel_alta = '#panel_al_asig';
$.asignatura.panel_list = '#panel_li_asig';
$.asignatura.panel_modi = '#panel_mo_asig';
$.asignatura.panel_borr = '#panel_bo_asig';
$.asignatura.panel_erro = '#panel_error';

/**
 Esta función hace la llamada REST al servidor y crea la tabla con todos los alumnos.
 */
$.asignatura.AsignaturaReadREST = function () {
    // con esta función jQuery hacemos la petición GET que hace el findAll()
    $.controller.doGet(
            this.HOST + this.URL,
            function (json) {
                $($.asignatura.panel_list).empty();
                $($.asignatura.panel_list).append('<h3>Listado de Asignaturas</h3>');
                var table = $('<table />').addClass('table table-stripped');

                table.append($('<thead />').append($('<tr />').append('<th>nombre</th>', '<th>id</th>', '<th>curso</th>', '<th>ciclo</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    tbody.append($('<tr />').append('<td>' + json[clave].nombre + '</td>',
                            '<td>' + json[clave].id + '</td>', '<td>' + json[clave].curso + '</td>'
                            + '</td>', '<td>' + json[clave].ciclo + '</td>'));
                }
                table.append(tbody);

                $($.asignatura.panel_list).append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
            });
};

/**
 Esta función carga los datos del formulario y los envía vía POST al servicio REST
 */
$.asignatura.AsignaturaCreateREST = function () {
    // Leemos los datos del formulario pidiendo a jQuery que nos de el valor de cada input.
    var datos = {
        'nombre': $("#c_as_nombre").val(),
        'curso' : $("#c_as_curso").val(),
        'ciclo' : $("#c_as_ciclo").val()
    };

    // comprobamos que en el formulario haya datos...
    if (datos.nombre.length > 2 && datos.curso >= 1 && datos.ciclo.length > 3) {
        // doPost(target, datos, fn_exito)
        $.controller.doPost(
            $.asignatura.HOST + $.asignatura.URL,
            datos,
            function () {
                // probamos que se ha actualizado cargando de nuevo la lista -no es necesario-
                $.asignatura.AsignaturaReadREST();
            });

        // cargamos el panel con id r_asignatura.
        $.controller.activate($.asignatura.panel_list);
    }

};

/**
 Crea un desplegable, un select, con todos los alumnos del servicio para seleccionar el alumno a eliminar
 */
$.asignatura.AsignaturaDeleteREST = function (id) {
    // si pasamos el ID directamente llamamos al servicio DELETE
    // si no, pintamos el formulario de selección para borrar.
    if (id !== undefined) {
        id = $('#d_as_sel').val();
        // doDelete (target, id, fn_exito)
        $.controller.doDelete(
            $.asignatura.HOST + $.asignatura.URL,
            id,
            function () {
                // probamos que se ha actualizado cargando de nuevo la lista -no es necesario-
                $.asignatura.AsignaturaReadREST();
                // cargamos el panel listado
                $.controller.activate($.asignatura.panel_list);
            });
    } else {
        // doGet (target, fn_exito)
        $.controller.doGet(
            this.HOST + this.URL,
            function (json) {
                // pintamos el formulario para ver a quien modificar
                $('select').material_select('destroy');
                $($.asignatura.panel_borr).empty();
                var formulario = $('<div />');
                formulario.addClass('input-field');
                var div_select = $('<div />');
                div_select.addClass('form-group');
                var select = $('<select id="d_as_sel" />');
                select.addClass('form-group');
                for (var clave in json) {
                    select.append('<option value="' + json[clave].nombre + '">' + json[clave].id + ' ' + json[clave].curso + ' ' + json[clave].ciclo + '</option>');
                }
                formulario.append(select);
                formulario.append('<div class="form-group"></div>').append('<div class="btn btn-danger" onclick="$.asignatura.AsignaturaDeleteREST(1)"> eliminar! </div>');
                $($.asignatura.panel_borr).append(formulario);
                $('select').material_select();
            }); 
    }

};

/**
 Función para la gestión de actualizaciones. Hay tres partes: 
 1) Listado 
 2) Formulario para modificación
 3) Envío de datos al servicio REST (PUT)
 */

$.asignatura.AsignaturaUpdateREST = function (id, envio) {
    // si no le pasamos parámetro, hay que sacar la lista para 
    // pulsar sobre quien queremos actualizar
    if (id === undefined) {
        $.controller.doGet(
            this.HOST + this.URL,
            function (json) {
                $($.asignatura.panel_list).empty();
                $($.asignatura.panel_list).append('<h3>Pulse sobre una asignatura</h3>');
                var table = $('<table />').addClass('table table-stripped');

                table.append($('<thead />').append($('<tr />').append('<th>nombre</th>', '<th>id</th>', '<th>curso</th>', '<th>ciclo</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<td>' + json[clave].nombre + '</td>', '<tr nombre="id_' + json[clave].id + '" onclick="$.asignatura.AsignaturaUpdateREST(' + json[clave].id + ')"/>').append('<td>' + json[clave].id + '</td>',
                            '<td>' + json[clave].curso + '</td>', '<td>' + json[clave].ciclo + '</td>'));
                }
                table.append(tbody);
                $($.asignatura.panel_list).append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($.asignatura.panel_list);
            });
    } else if (envio === undefined) {
        var seleccion = "#fila_" + id + " td";
        var as_nombre = ($(seleccion))[0];
        var as_id = ($(seleccion))[1];
        var as_curso = ($(seleccion))[2];
        var as_ciclo = ($(seleccion))[3];

        $("#u_as_nombre").val(as_nombre.childNodes[0].data);
        $("#u_as_id").val(as_id.childNodes[0].data);
        $("#u_as_curso").val(as_curso.childNodes[0].data);
        $("#u_as_ciclo").val(as_ciclo.childNodes[0].data);
        // cargamos el panel 
        $.controller.activate($.asignatura.panel_modi);
    } else {
        //HACEMOS LA LLAMADA REST
        var datos = {
            'nombre': $("#u_as_nombre").val(),
            'id': $("#u_as_id").val(),
            'curso': $("#u_as_curso").val(),
            'ciclo': $("#u_as_ciclo").val()
        };

        // comprobamos que en el formulario haya datos...
        if (datos.nombre.length > 2 && datos.curso >= 1 && datos.ciclo.length > 3) {
            // doPut(target, id, datos, fn_exito)
            $.controller.doPut(
                $.asignatura.HOST + $.asignatura.URL,
                $("#u_as_id").val(),
                datos,
                function() { 
                    // esto es lo que se ejecuta cuando tengamos éxito tras el PUT
                    $.asignatura.AsignaturaReadREST();
                }
            );

            // cargamos el panel con id r_alumno.
            $.controller.activate($.asignatura.panel_list);
        }
    }
};


/**
 Función para la gestión de errores y mensajes al usuario
 */
$.asignatura.error = function (title, msg) {
    $($.asignatura.panel_erro).empty();
    $($.asignatura.panel_erro).append('<h3>' + title + '</h3>');
    $($.asignatura.panel_erro).append('<p>' + msg + '</p>');

    // cargamos el panel con id r_alumno.
    $.controller.activate($.asignatura.panel_erro);
};