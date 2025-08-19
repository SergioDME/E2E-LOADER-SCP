describe('create and delete a new room ', function (){
    before(() => {
        // start recording
        cy.recordHar({ includeHosts: ['.*localhost:8080'],
            excludePaths: ['socket$','.woff$','.woff2$','.bmp$','.css$','.js$','.gif$','.ico$','.pe?g$','.png$','.swf$','.html$','.svg$']
        });
    });

    after(() => {
        cy.saveHar({ fileName: 'create_and_delete_room.har', outDir: 'cypress/e2e/hars' });
    });

    it('create and delete a new room with successfully',{defaultCommandTimeout:10000}, function (){
        cy.visit('http://localhost:8080/#/admin');
        cy.get('.col-2 > .btn').click();
        cy.get('[data-testid="username"]').clear();
        cy.get('[data-testid="username"]').type('admin');
        cy.get('[data-testid="password"]').clear();
        cy.get('[data-testid="password"]').type('password');
        cy.get('[data-testid="submit"]').click();

        cy.get('[data-testid="roomName"]').clear();
        cy.get('[data-testid="roomName"]').type('102');
        cy.get('#type').select('Twin');
        cy.get('#accessible').select('true');
        cy.get('#roomPrice').clear();
        cy.get('#roomPrice').type('120');
        cy.get('#wifiCheckbox').check();
        cy.get('#tvCheckbox').check();
        cy.get('#radioCheckbox').check();
        cy.get('#createRoom').click();

        cy.get('li[class="nav-item"]').eq(0).click();

        cy.wait(1000);

        cy.get('[data-testid="roomlisting"]').last().within(()=>{
            cy.get('span[class="fa fa-remove roomDelete"]').click();
        })

        cy.get('li[class="nav-item"]').eq(0).click();

        cy.wait(1000);

        cy.get('[data-testid="roomlisting"]').last().within(()=>{
            cy.get('div').eq(0).should('not.have.text','102');
        })


    })
})