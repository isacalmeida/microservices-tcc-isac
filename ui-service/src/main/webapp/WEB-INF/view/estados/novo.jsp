<%@ page contentType='text/html;charset=UTF-8' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form' %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title> Estados </title>

    <jsp:include page="../header.jsp" />

</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <jsp:include page="../main.jsp" />

    <jsp:include page="../menu.jsp" />

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Estados (${port})
                <small>Cadastro de Estados</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="<c:url value='/' />" ><i class="fa fa-tachometer-alt"></i> Início</a></li>
                <li><a href="<c:url value='/estados' />" >Estados</a></li>
                <li class="active">Novo</li>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <form:form method="post" action="/estados/salvar" modelAttribute="estado">
                    <div class="box-header with-border">
                        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <form:label path="id"> Id </form:label>
                            <form:input cssClass="form-control" path="id" readonly="true" />
                        </div>
                        <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <form:label path="dataCriacao"> Data de Criação </form:label>
                            <form:input cssClass="form-control" path="dataCriacao" readonly="true" />
                        </div>
                        <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <form:label path="dataAlteracao"> Data de Alteração </form:label>
                            <form:input cssClass="form-control" path="dataAlteracao" readonly="true"/>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <form:label path="descricao"> Descrição </form:label>
                            <form:input cssClass="form-control" path="descricao" size="50" />
                        </div>
                        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <form:label path="sigla"> Sigla </form:label>
                            <form:input cssClass="form-control" path="sigla" size="2" />
                        </div>
                        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <form:label path="ativo"> Ativo </form:label><br />
                            <form:checkbox cssClass="form-control bootstrapSwitch" id="ativo" path="ativo"
                                           data-on-color="success" data-off-color="danger" data-on-text="Sim" data-off-text="Não"  />
                        </div>
                    </div>
                    <div class="box-footer">
                        <div class="col-xs-12 col-sm-10 col-md-8 col-lg-8">
                            <button name="submit" type="submit" class="btn btn-success btn-flat"> Salvar </button>
                            <a href="<c:url value='/estados' />"><button type="button" class="btn btn-default btn-flat"> Voltar </button></a>
                        </div>
                    </div>
                </form:form>
            </div>
        </section>
    </div>

    <jsp:include page="../footer.jsp" />

</div>

</body>
</html>
