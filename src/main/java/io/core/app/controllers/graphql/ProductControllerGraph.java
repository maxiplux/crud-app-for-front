package io.core.app.controllers.graphql;

import io.core.app.exceptions.ResourceNotFoundException;
import io.core.app.models.Industry;
import io.core.app.models.Product;
import io.core.app.models.Sector;
import io.core.app.services.ProductSerivices;
import lombok.AllArgsConstructor;
import org.hibernate.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.print.Book;

