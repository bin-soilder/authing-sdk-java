package cn.authing.core.mgmt

import cn.authing.core.graphql.GraphQLCall
import cn.authing.core.graphql.GraphQLResponse
import cn.authing.core.mgmt.ManagementClient
import cn.authing.core.types.*
import com.google.gson.reflect.TypeToken

/**
 * 角色管理类
 */
class RolesManagementClient(private val client: ManagementClient) {
    /**
     * 获取角色列表
     */
    fun list(param: RolesParam): GraphQLCall<RolesResponse, PaginatedRoles> {
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<RolesResponse>>() {}) {
            it.result
        }
    }

    /**
     * 创建角色
     */
    fun create(param: CreateRoleParam): GraphQLCall<CreateRoleResponse, Role> {
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<CreateRoleResponse>>() {}) {
            it.result
        }
    }

    /**
     * 角色详情
     */
    fun detail(code: String): GraphQLCall<RoleResponse, Role> {
        val param = RoleParam(code);
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<RoleResponse>>() {}) {
            it.result
        }
    }

    /**
     * 更新角色
     */
    fun update(param: UpdateRoleParam): GraphQLCall<UpdateRoleResponse, Role> {
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<UpdateRoleResponse>>() {}) {
            it.result
        }
    }

    /**
     * 删除角色
     */
    fun delete(code: String): GraphQLCall<DeleteRoleResponse, CommonMessage> {
        val param = DeleteRoleParam(code)
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<DeleteRoleResponse>>() {}) {
            it.result
        }
    }

    /**
     * 批量删除角色
     */
    fun deleteMany(codeList: Iterable<String>): GraphQLCall<DeleteRolesResponse, BatchOperationResult> {
        val param = DeleteRolesParam(codeList)
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<DeleteRolesResponse>>() {}) {
            it.result
        }
    }

    /**
     * 获取用户列表
     */
    fun listUsers(code: String): GraphQLCall<RoleWithUsersResponse, PaginatedUsers> {
        val param = RoleWithUsersParam(code)
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<RoleWithUsersResponse>>() {}) {
            it.result.users
        }
    }

    /**
     * 批量添加用户
     */
    fun addUsers(code: String, userIds: Iterable<String>): GraphQLCall<AssignRoleResponse, CommonMessage> {
        val param = AssignRoleParam(code).withUserIds(userIds)
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<AssignRoleResponse>>() {}) {
            it.result
        }
    }

    /**
     * 批量移除用户
     */
    fun removeUsers(code: String, userIds: Iterable<String>): GraphQLCall<RevokeRoleResponse, CommonMessage> {
        val param = RevokeRoleParam(code).withUserIds(userIds)
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<RevokeRoleResponse>>() {}) {
            it.result
        }
    }

    /**
     * 获取策略列表
     */
    fun listPolicies(param: PolicyAssignmentsParam): GraphQLCall<PolicyAssignmentsResponse, PaginatedPolicyAssignment> {
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<PolicyAssignmentsResponse>>() {}) {
            it.result
        }
    }

    /**
     * 批量添加策略
     */
    fun addPolicies(
        code: String,
        policies: Iterable<String>
    ): GraphQLCall<AddPolicyAssignmentsResponse, CommonMessage> {
        val param =
            AddPolicyAssignmentsParam(policies, PolicyAssignmentTargetType.Role).withTargetIdentifiers(listOf(code))
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<AddPolicyAssignmentsResponse>>() {}) {
            it.result
        }
    }

    /**
     * 批量移除策略
     */
    fun removePolicies(
        code: String,
        policies: Iterable<String>
    ): GraphQLCall<RemovePolicyAssignmentsResponse, CommonMessage> {
        val param =
            RemovePolicyAssignmentsParam(policies, PolicyAssignmentTargetType.Role).withTargetIdentifiers(listOf(code))
        return client.createGraphQLCall(
            param.createRequest(),
            object : TypeToken<GraphQLResponse<RemovePolicyAssignmentsResponse>>() {}) {
            it.result
        }
    }
}