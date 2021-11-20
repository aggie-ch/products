package io.github.aggie.users;

import io.github.aggie.common.PagedResult;
import io.github.aggie.common.web.PagedResultTransferObject;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "emailAddress", target = "email")
    User toUser(UserTransferObject userTransferObject);

    @InheritInverseConfiguration
    UserTransferObject toUserTransferObject(User user);

    /*@IterableMapping(elementTargetType = UserTransferObject.class)
    List<UserTransferObject> toUserTransferObjects(List<User> users);*/

    PagedResultTransferObject<UserTransferObject> toUserTransferObjectsPage(PagedResult<User> usersPage);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateUser(User user, @MappingTarget User userToUpdate);
}
