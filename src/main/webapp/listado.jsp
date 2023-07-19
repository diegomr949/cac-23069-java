<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.com.codoacodo23069.Producto" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="styles.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="navbar.jsp"></jsp:include>
    <div class="container">
      <div class="row">
        <div class="col-12">
            <% String ok = (String)request.getAttribute("eliminadook");%>
            <% String fail = (String)request.getAttribute("eliminadofail");%>

            <%
            if(ok != null) {
            %>
                <div class="alert alert-success">
                    exito
                </div>
            <%
            }
            %>
            <%
            if(fail != null) {
            %>
                <div class="alert alert-danger">
                    error
                </div>
            <%
            }
            %>
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">T&Iacute;TULO</th>
                    <th scope="col">PRECIO</th>
                    <th scope="col">C&Oacute;DIGO</th>
                    <th scope="col">AUTOR</th>
                    <th scope="col">FECHA</th>
                    <th scope="col">------</th>
                  </tr>
                </thead>
                <tbody>
                    <% 
                    //bajo el dato del request, que guardo el controller
                    ArrayList<Producto> listado = (ArrayList<Producto>)request.getAttribute("listado");//[]
                    for(Producto unarticulo : listado) {
                    %>
                        <tr>
                            <th scope="row"><%=unarticulo.getId()%></th>
                            <td><%=unarticulo.getTitulo()%></td>
                            <td><%=unarticulo.getPrecio()%></td>
                            <td><%=unarticulo.getCodigo()%></td>
                            <td><%=unarticulo.getAutor()%></td>
                            <td><%=unarticulo.getFecha()%></td>
                            <td>
                                
                                <a href="<%=request.getContextPath()%>/EliminarController?id=<%=unarticulo.getId()%>" 
                                    class="btn btn-danger" 
                                    tabindex="-1"
                                    role="button"
                                    aria-disabled="true">
                                    Eliminar
                                    
                                </a>
                                <a href="<%=request.getContextPath()%>/EditarProductoController?id=<%=unarticulo.getId()%>" 
                                    class="btn btn-danger" 
                                    tabindex="-1"
                                    role="button"
                                    aria-disabled="true">
                                    Editar
                                    
                                </a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
              </table>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>