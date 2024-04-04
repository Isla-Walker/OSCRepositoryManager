<#import "base.ftl" as base>

<@base.content>
    <div class="content">
        <h1 class="content-title font-size-22"> <!-- font-size-22 = font-size: 2.2rem (22px) -->
            Users
        </h1>
        <table class="table table-bordered table-hover table-striped">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>Username</th>
                <th>Email Address</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <th style="text-align: center"><i class="fa-solid fa-user"></i></th>
                    <th>${user.getId()}</th>
                    <th>${user.getUsername()}</th>
                    <th>${user.getEmail()}</th>
                    <th>${user.getRole()}</th>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@base.content>