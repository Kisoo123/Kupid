<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	 .bg-footer{
		background-color: #f8f9fa;
	}
</style>
  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 border-top bg-footer">
    <div class="col-md-4 d-flex align-items-center">
      <a href="/" class="mb-3 me-2 mb-md-0 text-body-secondary text-decoration-none lh-1">
        <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
      </a>
      <div>
            <h4 class="mb-0 text-body-secondary">Copyright © Team KPL</h4>
            <h5 class="text-body-secondary">All rights reserved.</h5>
    </div>
   </div>

    <ul class="nav col-md-4 justify-content-end list-unstyled d-flex ms-4">
      <li class="nav-item"><a href="<%=request.getContextPath()%>/policies/terms?no=1" class="nav-link px-2 text-body-secondary">Member-Policy</a></li>
      <li class="nav-item"><a href="<%=request.getContextPath()%>/policies/terms?no=2" class="nav-link px-2 text-body-secondary">Privacy-Policy</a></li>
    </ul>
  </footer>
