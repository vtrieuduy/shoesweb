<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<br />

<style>
.thumbnail:hover {
	box-shadow: 0px 0px 7px;
}
</style>


	<h3 class="title">

			<b>( ${list.size()} </b> sản phẩm)

	</h3>
	<c:forEach var="p" items="${list}">
		<div class="col-sm-3"
			style="padding: 5px 5px 5px 5px; position: relative;">
			<div class="thumbnail" style="border-radius: 7px;">

				<a href="/product/detail/${p.id}"> <c:choose>
						<c:when test="${p.discount > 0 }">
							<p
								style="background-color: red; width: 30px; height: 30px; text-align: right; float: right; position: relative; border-radius: 30px">
								<span
									style="margin-left: -28px; margin-top: 5px; font-size: 14px; color: #fff; position: absolute;">
									-<f:formatNumber value="${p.discount}" type="percent" />
								</span>
							</p>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose> <img style="margin-right: 57px; margin-top: 5px; border-radius: 7px;"
					class="estore-prod" src="/static/images/products/${p.image}"
					title="${p.name}">
				</a>

				<div class="caption">
					<p style="padding: 3px; text-align: center; color: black">
						<b>${p.name}</b>
					</p>
					<p
						style="padding: 3px; font-size: 15px; text-align: center; font-weight: bold;">
						<c:choose>
							<c:when test="${p.discount <= 0 }">
								<span style="text-align: left; font-weight: bold;color:red"><f:formatNumber
										value="${p.unitPrice}" pattern="#,###" />VNĐ </span>
							</c:when>
							<c:otherwise>
								<span
									style="text-decoration: line-through; color: Gray; font-weight: bold;">
									<f:formatNumber value="${p.unitPrice}" pattern="#,###" /> VNĐ
								</span>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${p.discount > 0 }">
								<span style="margin-left: 5px; font-weight: bold;color:red"> <f:formatNumber
										value="${p.unitPrice * (1 - p.discount)}" pattern="#,###" />
									VNĐ
								</span>
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>

					</p>
					<br /> <br />
					<div data-id="${p.id}" class="pull-center text-center">
						<button class="btn btn-sm btn-danger btn-add-to-cart">
							<i class="glyphicon glyphicon-shopping-cart"></i>
						</button>
						<button class="btn btn-sm btn-secondary btn-star">
							<i class="fa fa-thumbs-up"></i>
						</button>
						<button class="btn btn-sm btn-success btn-open-dialog"
							data-toggle="modal" data-target="#myModal">
							<i class="glyphicon glyphicon-envelope"></i>
						</button>
					</div>
				</div>


			</div>
		</div>

	</c:forEach>



<jsp:include page="dialog.jsp" />

<style>
.pull-center {
	margin-top: -30px;
}

.caption {
	height: 150px
}
</style>