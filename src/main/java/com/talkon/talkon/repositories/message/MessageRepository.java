package com.talkon.talkon.repositories.message;

import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.projections.message.LastMessageProjection;
import com.talkon.talkon.projections.message.MessageProjection;
import com.talkon.talkon.projections.message.ParentMessageProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends AbstractRepository<Message, String> {


    @Query(nativeQuery = true, value = "\n" +
            "            select * from (select id,\n" +
            "                                  body,\n" +
            "                                  created_at as \"createdAt\",\n" +
            "                                  is_read    as \"isRead\",\n" +
            "                                  from_id    as \"senderId\",\n" +
            "                                  file_path  as \"fileLink\",\n" +
            "                                  updated_at as \"updatedAt\",\n" +
            "                                  parent\n" +
            "                           from conversation.messages\n" +
            "                           where chat_id = :chatId\n" +
            "                           order by created_at desc\n" +
            "                           limit (case when (select count(id) from conversation.messages where chat_id=:chatId and not is_read)>:size\n" +
            "                           then (select count(id) from conversation.messages where chat_id=:chatId and not is_read) else :size end) offset :size*:page) inn order by \"createdAt\"\n" +
            "           ")
    List<MessageProjection> getAllMessages(String chatId, int size, long page);


    @Query(nativeQuery = true, value = "select id, body from conversation.messages where parent = :parentId")
    ParentMessageProjection getParentMessage(String parentId);


    @Query(nativeQuery = true, value = "\n" +
            "            select messages.body,\n" +
            "                            messages.created_at as \"createdAt\"\n" +
            "                    from conversation.messages join conversation.chats c on c.id = messages.chat_id\n" +
            "                    where c.id = :chatId and messages.created_at = (select max(messages.created_at) from conversation.messages where chat_id = :chatId)\n" +
            "            ")
    LastMessageProjection getLastMessage(String chatId);

    @Query(nativeQuery = true, value = "\n" +
            "            select id,\n" +
            "                               body,\n" +
            "                               created_at as \"createdAt\",\n" +
            "                               is_read    as \"isRead\",\n" +
            "                               from_id    as \"senderId\",\n" +
            "                               file_path  as \"fileLink\",\n" +
            "                               updated_at as \"updatedAt\",\n" +
            "                               parent     as \"parentId\"\n" +
            "                        from conversation.messages\n" +
            "                        where messages.id = :id\n" +
            "            ")
    MessageProjection getMessageById(String id);

    @Modifying
    @Query(value = "update conversation.messages set is_read=true where id=:messageId", nativeQuery = true)
    void makeMessageAsRead(String messageId);
}
